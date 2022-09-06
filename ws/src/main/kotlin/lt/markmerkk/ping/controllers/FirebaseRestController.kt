package lt.markmerkk.ping.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import lt.markmerkk.ping.entities.ContentDetailRequest
import lt.markmerkk.ping.entities.FBMessageRequest
import lt.markmerkk.ping.entities.PingEntry
import lt.markmerkk.ping.firebase.FBMessaging
import lt.markmerkk.ping.repositories.PingRepository
import lt.markmerkk.ping.utils.TimeProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/fb")
open class FirebaseRestController(
    @Autowired private val timeProvider: TimeProvider,
    @Autowired private val fbMessaging: FBMessaging,
) {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @RequestMapping(
        value = ["/send"],
        method = [RequestMethod.POST],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    fun pingDetail(
        @RequestBody(required = true) fbMessageRequest: FBMessageRequest,
    ): HttpStatus {
        fbMessaging.sendMessage(
            registrationToken = fbMessageRequest.token,
            message = fbMessageRequest.message,
        )
        return HttpStatus.OK
    }

    companion object {
        private val l = LoggerFactory.getLogger(FirebaseRestController::class.java)!!
    }
}