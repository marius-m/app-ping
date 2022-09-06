package lt.markmerkk.ping.firebase

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FBMessaging(
    @Autowired val fbInstance: FBInstance
) {

    private val fbMessaging: FirebaseMessaging = FirebaseMessaging
        .getInstance(fbInstance.app())

    fun sendMessage(registrationToken: String, message: String) {
        val fbMessage: Message = Message.builder()
            // .putData("score", "850")
            // .putData("time", "2:45")
            .setToken(registrationToken)
            .build()
        val response = fbMessaging.send(fbMessage)
        l.info("sendMessage(message: {}, response: {})", message, response)
    }

    companion object {
        private val l = LoggerFactory.getLogger(FBMessaging::class.java)!!
    }
}