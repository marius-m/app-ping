package lt.markmerkk.ping.utils

import org.joda.time.DateTimeConstants
import java.time.DayOfWeek

object DateMaps {

    fun compatDayOfWeek(inputString: String): String {
        val foundKey = dayOfWeekPossibleValues.keys
            .firstOrNull { inputString.contains(it) }
        if (foundKey != null) {
            val replaceValue = dayOfWeekCompat.getValue(dayOfWeekPossibleValues.getValue(foundKey))
            return inputString.replace(foundKey, replaceValue)
        }
        return inputString
    }

    fun compatMonthOfYear(inputString: String): String {
        val foundKey = monthOfYearPossibleValues.keys
            .firstOrNull { inputString.contains(it) }
        if (foundKey != null) {
            val replaceValue = monthOfYearCompat.getValue(monthOfYearPossibleValues.getValue(foundKey))
            return inputString.replace(foundKey, replaceValue)
        }
        return inputString
    }

    private val dayOfWeekCompat: Map<DayOfWeek, String> = mapOf(
        DayOfWeek.MONDAY to "PR",
        DayOfWeek.TUESDAY to "AN",
        DayOfWeek.WEDNESDAY to "TR",
        DayOfWeek.THURSDAY to "KT",
        DayOfWeek.FRIDAY to "PN",
        DayOfWeek.SATURDAY to "ŠT",
        DayOfWeek.SUNDAY to "SK",
    )

    private val dayOfWeekPossibleValues: Map<String, DayOfWeek> = mapOf(
        "Pirmadienis" to DayOfWeek.MONDAY,
        "Pr" to DayOfWeek.MONDAY,
        "pirmadienis" to DayOfWeek.MONDAY,
        "PIRMADIENIS" to DayOfWeek.MONDAY,
        "pr" to DayOfWeek.MONDAY,
        "PR" to DayOfWeek.MONDAY,
        "An" to DayOfWeek.TUESDAY,
        "an" to DayOfWeek.TUESDAY,
        "AN" to DayOfWeek.TUESDAY,
        "antradienis" to DayOfWeek.TUESDAY,
        "ANTRADIENIS" to DayOfWeek.TUESDAY,
        "Antradienis" to DayOfWeek.TUESDAY,
        "TREČIADIENIS" to DayOfWeek.WEDNESDAY,
        "trečiadienis" to DayOfWeek.WEDNESDAY,
        "Trečiadienis" to DayOfWeek.WEDNESDAY,
        "Tr" to DayOfWeek.WEDNESDAY,
        "tr" to DayOfWeek.WEDNESDAY,
        "TR" to DayOfWeek.WEDNESDAY,
        "Kt" to DayOfWeek.THURSDAY,
        "KETVIRTADIENIS" to DayOfWeek.THURSDAY,
        "ketvirtadienis" to DayOfWeek.THURSDAY,
        "Ketvirtadienis" to DayOfWeek.THURSDAY,
        "kt" to DayOfWeek.THURSDAY,
        "KT" to DayOfWeek.THURSDAY,
        "Penktadienis" to DayOfWeek.FRIDAY,
        "penktadienis" to DayOfWeek.FRIDAY,
        "Pn" to DayOfWeek.FRIDAY,
        "pn" to DayOfWeek.FRIDAY,
        "PN" to DayOfWeek.FRIDAY,
        "PENKTADIENIS" to DayOfWeek.FRIDAY,
        "ŠEŠTADIENIS" to DayOfWeek.SATURDAY,
        "Št" to DayOfWeek.SATURDAY,
        "Šeštadienis" to DayOfWeek.SATURDAY,
        "št" to DayOfWeek.SATURDAY,
        "ŠT" to DayOfWeek.SATURDAY,
        "šeštadienis" to DayOfWeek.SATURDAY,
        "sekmadienis" to DayOfWeek.SUNDAY,
        "Sekmadienis" to DayOfWeek.SUNDAY,
        "sk" to DayOfWeek.SUNDAY,
        "SK" to DayOfWeek.SUNDAY,
        "Sk" to DayOfWeek.SUNDAY,
        "SEKMADIENIS" to DayOfWeek.SUNDAY,
    )

    private val monthOfYearCompat: Map<Int, String> = mapOf(
        DateTimeConstants.JANUARY to "SAUS.",
        DateTimeConstants.FEBRUARY to "VAS.",
        DateTimeConstants.MARCH to "KOV.",
        DateTimeConstants.APRIL to "BAL.",
        DateTimeConstants.MAY to "GEG.",
        DateTimeConstants.JUNE to "BIRŽ.",
        DateTimeConstants.JULY to "LIEP.",
        DateTimeConstants.AUGUST to "RUGP.",
        DateTimeConstants.SEPTEMBER to "RUGS.",
        DateTimeConstants.OCTOBER to "SPAL.",
        DateTimeConstants.NOVEMBER to "LAPKR.",
        DateTimeConstants.DECEMBER to "GRUOD.",
    )

    private val monthOfYearPossibleValues: Map<String, Int> = mapOf(
        "Sau" to DateTimeConstants.JANUARY,
        "SAU" to DateTimeConstants.JANUARY,
        "SAUSIO" to DateTimeConstants.JANUARY,
        "sausio" to DateTimeConstants.JANUARY,
        "sau" to DateTimeConstants.JANUARY,
        "vasaris" to DateTimeConstants.FEBRUARY,
        "VASARIS" to DateTimeConstants.FEBRUARY,
        "Vas" to DateTimeConstants.FEBRUARY,
        "VAS" to DateTimeConstants.FEBRUARY,
        "vas" to DateTimeConstants.FEBRUARY,
        "kovas" to DateTimeConstants.MARCH,
        "kov" to DateTimeConstants.MARCH,
        "KOVAS" to DateTimeConstants.MARCH,
        "Kov" to DateTimeConstants.MARCH,
        "KOV" to DateTimeConstants.MARCH,
        "balandis" to DateTimeConstants.APRIL,
        "bal" to DateTimeConstants.APRIL,
        "Bal" to DateTimeConstants.APRIL,
        "BAL" to DateTimeConstants.APRIL,
        "BALANDIS" to DateTimeConstants.APRIL,
        "GEGUŽĖ" to DateTimeConstants.MAY,
        "Geg" to DateTimeConstants.MAY,
        "GEG" to DateTimeConstants.MAY,
        "gegužė" to DateTimeConstants.MAY,
        "geg" to DateTimeConstants.MAY,
        "birželis" to DateTimeConstants.JUNE,
        "bir" to DateTimeConstants.JUNE,
        "BIRŽELIS" to DateTimeConstants.JUNE,
        "Bir" to DateTimeConstants.JUNE,
        "BIR" to DateTimeConstants.JUNE,
        "lie" to DateTimeConstants.JULY,
        "LIEPA" to DateTimeConstants.JULY,
        "liepa" to DateTimeConstants.JULY,
        "Lie" to DateTimeConstants.JULY,
        "LIE" to DateTimeConstants.JULY,
        "Rgp" to DateTimeConstants.AUGUST,
        "RGP" to DateTimeConstants.AUGUST,
        "rugpjūtis" to DateTimeConstants.AUGUST,
        "rgp" to DateTimeConstants.AUGUST,
        "RUGPJŪTIS" to DateTimeConstants.AUGUST,
        "rugsėjis" to DateTimeConstants.SEPTEMBER,
        "Rgs" to DateTimeConstants.SEPTEMBER,
        "RGS" to DateTimeConstants.SEPTEMBER,
        "RUGSĖJIS" to DateTimeConstants.SEPTEMBER,
        "rgs" to DateTimeConstants.SEPTEMBER,
        "Spa" to DateTimeConstants.OCTOBER,
        "SPA" to DateTimeConstants.OCTOBER,
        "spa" to DateTimeConstants.OCTOBER,
        "SPALIS" to DateTimeConstants.OCTOBER,
        "spalis" to DateTimeConstants.OCTOBER,
        "lap" to DateTimeConstants.NOVEMBER,
        "lapkritis" to DateTimeConstants.NOVEMBER,
        "LAPKRITIS" to DateTimeConstants.NOVEMBER,
        "Lap" to DateTimeConstants.NOVEMBER,
        "LAP" to DateTimeConstants.NOVEMBER,
        "GRUODIS" to DateTimeConstants.DECEMBER,
        "Grd" to DateTimeConstants.DECEMBER,
        "GRD" to DateTimeConstants.DECEMBER,
        "grd" to DateTimeConstants.DECEMBER,
        "gruodis" to DateTimeConstants.DECEMBER,
    )
}

