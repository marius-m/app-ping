package lt.markmerkk.ping.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import lt.markmerkk.ping.firebase.entities.FBCreds
import lt.markmerkk.ping.utils.LogUtils.withLogInstance
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class FBInstance(
    @Autowired val fbCreds: FBCreds
) {

    private lateinit var app: FirebaseApp

    init {
        l.info("init(creds: $fbCreds)".withLogInstance(this))
    }

    @PostConstruct
    fun onInit() {
        val options: FirebaseOptions = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(fbCreds.configAsStream()))
            .build()

        app = FirebaseApp.initializeApp(options)
        l.info("onInit(app: $app)".withLogInstance(this))
    }

    fun app(): FirebaseApp = app

    companion object {
        private val l = LoggerFactory.getLogger(FBInstance::class.java)!!
    }
}