package lt.markmerkk.ping.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import lt.markmerkk.ping.entities.ContentDetailResponse
import lt.markmerkk.ping.entities.PingEntry
import lt.markmerkk.ping.repositories.PingRepository
import lt.markmerkk.ping.utils.TimeProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
open class BasicRestController(
    @Autowired private val pingRepository: PingRepository,
    @Autowired private val timeProvider: TimeProvider,
) {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @RequestMapping(
        value = ["/ping"],
        method = [RequestMethod.POST],
        produces = ["application/json"]
    )
    @ResponseBody
    fun ping(
        @RequestHeader(value = "User-Agent", required = false) userAgent: String?,
        @RequestBody(required = false) content: String?,
    ): HttpStatus {
        l.info("ping(userAgent: '{}', content: '{}')", userAgent, content)
        return HttpStatus.OK
    }

    @RequestMapping(
        value = ["/pingDetail"],
        method = [RequestMethod.POST],
        produces = ["application/json"]
    )
    @ResponseBody
    fun pingDetail(
        @RequestHeader(value = "User-Agent", required = false) userAgent: String?,
        @RequestBody(required = true) contentRaw: ContentDetailResponse,
    ): HttpStatus {
        val pingEntry = PingEntry.fromResponse(
            timeProvider = timeProvider,
            userAgent = userAgent ?: "",
            response = contentRaw,
        )
        l.info("pingDetail(userAgent: '{}', content: '{}')", userAgent, pingEntry)
        pingRepository.storePing(pingEntry = pingEntry)
        return HttpStatus.OK
    }

    companion object {
        private val l = LoggerFactory.getLogger(BasicRestController::class.java)!!
    }
}