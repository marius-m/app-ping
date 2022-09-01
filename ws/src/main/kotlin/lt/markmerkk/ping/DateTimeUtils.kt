package lt.markmerkk.ping

import java.time.Duration
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    val zoneId = ZoneId.systemDefault()
    val dateFormatterDefault = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dtFormatterDefault = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dtFormatterFull = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun legacyDateToOdt(date: java.util.Date): OffsetDateTime {
        return OffsetDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC)
    }

    fun odtFullAsString(odt: OffsetDateTime): String {
        return dtFormatterFull.format(odt)
    }

    fun durationToString(diff: Duration): String {
        return String.format(
            "~%dh %02dm %02ds",
            diff.toHours(),
            diff.toMinutesPart(),
            diff.toSecondsPart()
        )
    }
}