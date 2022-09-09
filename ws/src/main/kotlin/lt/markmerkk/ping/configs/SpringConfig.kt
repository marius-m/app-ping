package lt.markmerkk.ping.configs

import lt.markmerkk.ping.firebase.FBMessaging
import lt.markmerkk.ping.repositories.FirebaseRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled


@Configuration
@EnableScheduling
open class SpringConfig(
    @Autowired private val firebaseRepository: FirebaseRepository,
    @Autowired private val fbMessaging: FBMessaging,
) {

    @Scheduled(initialDelay = MINUTE * 1, fixedDelay = MINUTE * 15)
    fun schedulePeriodicPing() {
        val registrationTokens = firebaseRepository.deviceList()
            .map { device -> device.token }
        fbMessaging.sendMessageBundle(
            registrationTokens = registrationTokens,
            message = "ping",
        )
        l.info("periodicPing()")
    }

    companion object {
        const val MILLI = 1L
        const val SECOND = MILLI * 1000L
        const val MINUTE = SECOND * 60L
        const val HOUR = MINUTE * 60L

        private val l = LoggerFactory.getLogger(SpringConfig::class.java)!!
    }

}