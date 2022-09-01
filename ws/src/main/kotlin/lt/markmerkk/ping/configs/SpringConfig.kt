package lt.markmerkk.ping.configs

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled


@Configuration
@EnableScheduling
open class SpringConfig {

    @Scheduled(initialDelay = SECOND * 10, fixedDelay = MINUTE * 30)
    fun schedulePerioditPing() {
        l.debug("periodicPing")
    }

    companion object {
        const val MILLI = 1L
        const val SECOND = MILLI * 1000L
        const val MINUTE = SECOND * 60L
        const val HOUR = MINUTE * 60L

        private val l = LoggerFactory.getLogger(SpringConfig::class.java)!!
    }

}