package lt.markmerkk.ping.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
open class BasicRestController {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @RequestMapping(
        value = ["/ping"],
        method = [RequestMethod.POST],
        produces = ["application/json"]
    )
    @ResponseBody
    fun ping(
        @RequestBody(required = false) content: String?,
    ): HttpStatus {
        l.info("ping(content: '{}')", content)
        return HttpStatus.OK
    }

    companion object {
        private val l = LoggerFactory.getLogger(BasicRestController::class.java)!!
    }
}