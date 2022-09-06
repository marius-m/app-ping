package lt.markmerkk.ping.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import lt.markmerkk.ping.entities.entries.DeviceRegisterEntry
import lt.markmerkk.ping.entities.requests.FBDeviceRegisterRequest
import lt.markmerkk.ping.entities.requests.FBDeviceUnregisterRequest
import lt.markmerkk.ping.entities.requests.FBMessageRequest
import lt.markmerkk.ping.entities.requests.FBMessageToRegisteredRequest
import lt.markmerkk.ping.firebase.FBMessaging
import lt.markmerkk.ping.repositories.FirebaseRepository
import lt.markmerkk.ping.utils.TimeProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/fb")
open class FirebaseRestController(
    @Autowired private val timeProvider: TimeProvider,
    @Autowired private val fbMessaging: FBMessaging,
    @Autowired private val firebaseRepository: FirebaseRepository,
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
    fun send(
        @RequestBody(required = true) fbMessageRequest: FBMessageRequest,
    ): HttpStatus {
        fbMessaging.sendMessage(
            registrationToken = fbMessageRequest.token,
            message = fbMessageRequest.message,
        )
        return HttpStatus.OK
    }

    @RequestMapping(
        value = ["/devices/send"],
        method = [RequestMethod.POST],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    fun sendToRegistered(
        @RequestBody(required = true) fbMessageRequest: FBMessageToRegisteredRequest,
    ): HttpStatus {
        val registrationTokens = firebaseRepository.deviceList()
            .map { device -> device.token }
        fbMessaging.sendMessageBundle(
            registrationTokens = registrationTokens,
            message = fbMessageRequest.message,
        )
        return HttpStatus.OK
    }

    @RequestMapping(
        value = ["/devices/register"],
        method = [RequestMethod.POST],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    fun devicesRegister(
        @RequestHeader(value = "User-Agent", required = false) userAgent: String?,
        @RequestBody(required = true) deviceRegisterRequest: FBDeviceRegisterRequest,
    ): HttpStatus {
        val deviceRegisterEntry = DeviceRegisterEntry(
            userAgent = userAgent ?: "",
            deviceName = deviceRegisterRequest.deviceName,
            token = deviceRegisterRequest.token,
        )
        l.info("devicesRegister(entry: {})", deviceRegisterEntry)
        firebaseRepository.deviceRegister(
            deviceRegisterEntry = deviceRegisterEntry
        )
        return HttpStatus.OK
    }

    @RequestMapping(
        value = ["/devices/unregister"],
        method = [RequestMethod.POST],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    fun devicesUnregisterByToken(
        @RequestHeader(value = "User-Agent", required = false) userAgent: String?,
        @RequestBody(required = true) deviceUnregisterRequest: FBDeviceUnregisterRequest,
    ): HttpStatus {
        l.info("devicesUnregister(token: {})", deviceUnregisterRequest.token)
        val result = firebaseRepository.deviceUnregister(
            token = deviceUnregisterRequest.token,
        )
        return when (result) {
            true -> HttpStatus.OK
            false -> HttpStatus.NOT_FOUND
        }
    }

    @RequestMapping(
        value = ["/devices/clear"],
        method = [RequestMethod.POST],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    fun devicesClear(
        @RequestHeader(value = "User-Agent", required = false) userAgent: String?,
    ): HttpStatus {
        l.info("devicesClear()")
        firebaseRepository.deviceClearAll()
        return HttpStatus.OK
    }

    @RequestMapping(
        value = ["/devices/list"],
        method = [RequestMethod.GET],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    fun devicesList(): List<DeviceRegisterEntry> {
        l.info("devicesList()")
        return firebaseRepository.deviceList()
    }

    companion object {
        private val l = LoggerFactory.getLogger(FirebaseRestController::class.java)!!
    }
}