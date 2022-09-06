package lt.markmerkk.ping.firebase

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
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
        l.info("sendMessage(message: {})", message)
        val fbMessage: Message = Message.builder()
            .putData(BUNDLE_KEY_MESSAGE, message)
            .setToken(registrationToken)
            .build()
        val response = fbMessaging.send(fbMessage)
        l.info("sendMessage.result(response: {})", response)
    }

    fun sendMessageBundle(
        registrationTokens: List<String>,
        message: String,
    ) {
        l.info("sendMessageBundle()")
        if (registrationTokens.isEmpty()) {
            l.info("sendMessageBundle.failureNoTokens()")
            return
        }
        val fbMessage = MulticastMessage.builder()
            .putData(BUNDLE_KEY_MESSAGE, message)
            // .putData("time", "2:45")
            .addAllTokens(registrationTokens)
            .build()
        val response = fbMessaging.sendMulticast(fbMessage)
        if (response.failureCount > 0) {
            val responses = response.responses
            val failedTokens = mutableListOf<String>()
            for (i in responses.indices) {
                if (!responses[i].isSuccessful) {
                    failedTokens.add(registrationTokens[i])
                }
            }
            l.info("sendMessageBundle.failureSending(failureTokens: {})", failedTokens)
        }
        l.info("sendMessageBundle.result(response: {})", response)
    }

    companion object {
        private val l = LoggerFactory.getLogger(FBMessaging::class.java)!!
        const val BUNDLE_KEY_MESSAGE = "message"
    }
}