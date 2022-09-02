package lt.markmerkk.ping.utils

import org.joda.time.format.DateTimeFormat
import java.util.Locale

object AppDTUtils {

    init {
        System.setProperty("java.locale.providers", "COMPAT,CLDR")
    }

    private val fmt1 = DateTimeFormat.forPattern("EE, dd MMM yyyy HH:mm:ss Z")
    private val fmt2 = DateTimeFormat.forPattern("EE, dd MMM yyyy HH:mm:ss z")
    private val fmt3 = DateTimeFormat.forPattern("yyyy.MM.dd HH:mm")
    private val fmt4 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
    private val fmt5 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    private val formatterLt = fmt1.withLocale(Locale("lt", "LT"))
    private val formatterEn = fmt1.withLocale(Locale.ENGLISH)
    private val formatterEn2 = fmt2.withLocale(Locale.ENGLISH)
    private val formatterNum1 = fmt3.withLocale(Locale.ENGLISH)
    private val formatterNum2 = fmt4.withLocale(Locale.ENGLISH)
    private val formatterNum3 = fmt5.withLocale(Locale.ENGLISH)

    //private val knownFormatters = listOf(formatterEn, formatterLt, formatterEn2)
    private val knownFormatters = listOf(
        formatterEn,
        formatterLt,
        formatterEn2,
        formatterNum1,
        formatterNum2,
        formatterNum3,
    )

    /**
     * Date that comes in weird form, parses out to [LocalDateTime]
     * Ex. 1: Tr, 13 Vas 2019 18:42:10 +0200
     * Ex. 2: Tue, 16 Feb 2021 13:38:03 GMT
     * Ex. 3: 2022.04.24 11:00
     * Ex. 4: 2022-07-07 23:45:00
     * Ex. 5: 2022-07-07T20:45:00Z
     */
    fun dateTimeFromStringOrDefault(
        dtDefault: java.time.LocalDateTime,
        dtAsString: String
    ): java.time.LocalDateTime {
        val defaultDateTime = dtDefault.withHour(0)
            .withMinute(0)
            .withSecond(0)
        // Compat is needed for j11
        val publishDateTimeCompat1 = DateMaps.compatDayOfWeek(dtAsString)
        val publishDateTimeCompat2 = DateMaps.compatMonthOfYear(publishDateTimeCompat1)
        val parseDateTime = knownFormatters
            .mapNotNull { formatter ->
                try {
                    formatter.parseDateTime(publishDateTimeCompat2)
                } catch (e: IllegalArgumentException) {
                    null
                }
            }.map { jodaDateTime ->
                java.time.LocalDateTime.of(
                    jodaDateTime.year,
                    jodaDateTime.monthOfYear,
                    jodaDateTime.dayOfMonth,
                    jodaDateTime.hourOfDay,
                    jodaDateTime.minuteOfHour,
                    jodaDateTime.secondOfMinute,
                )
            }.firstOrNull()
        return parseDateTime ?: defaultDateTime
    }
}


