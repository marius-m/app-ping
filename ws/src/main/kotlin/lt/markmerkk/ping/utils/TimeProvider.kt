package lt.markmerkk.ping.utils

import java.time.Clock
import java.time.LocalDateTime

class TimeProvider(
        private val clock: Clock
) {
    fun now(): LocalDateTime = LocalDateTime.now(clock)
}