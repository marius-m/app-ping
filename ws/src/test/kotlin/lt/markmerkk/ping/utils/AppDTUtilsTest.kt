package lt.markmerkk.ping.utils

import org.assertj.core.api.Assertions
import org.junit.Test
import java.time.LocalDateTime

class AppDTUtilsTest {

    private val dtDefault = LocalDateTime.of(2019, 2, 13, 18, 42, 10)

    @Test
    fun valid_lt1() {
        val postDateTime = "Pr, 7 Sau 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                1,
                7,
                18,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt2() {
        val postDateTime = "Tr, 13 Vas 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                2,
                13,
                18,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt3() {
        val postDateTime = "Kt, 7 Kov 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                3,
                7,
                18,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt4() {
        val postDateTime = "Pn, 12 Bal 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                4,
                12,
                19,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt5() {
        val postDateTime = "Tr, 15 Geg 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                5,
                15,
                19,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt6() {
        val postDateTime = "Tr, 19 Bir 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                6,
                19,
                19,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt7() {
        val postDateTime = "Št, 20 Lie 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                7,
                20,
                19,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt8() {
        val postDateTime = "An, 20 Rgp 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                8,
                20,
                19,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt9() {
        val postDateTime = "Kt, 12 Rgs 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                9,
                12,
                19,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt10() {
        val postDateTime = "Tr, 16 Spa 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                10,
                16,
                19,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt11() {
        val postDateTime = "Kt, 14 Lap 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                11,
                14,
                18,
                42,
                10
            )
        )
    }

    @Test
    fun valid_lt12() {
        val postDateTime = "Sk, 1 Grd 2019 18:42:10 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                12,
                1,
                18,
                42,
                10
            )
        )
    }

    @Test
    fun valid2() {
        val postDateTime = "Fri, 08 Feb 2019 16:46:59 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2019,
                2,
                8,
                16,
                46,
                59
            )
        )
    }

    @Test
    fun valid3() {
        val postDateTime = "An, 08 Grd 2020 19:09:48 +0200"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2020,
                12,
                8,
                19,
                9,
                48
            )
        )
    }

    @Test
    fun valid4_en2() {
        val postDateTime = "Tue, 16 Feb 2021 13:38:03 GMT"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2021,
                2,
                16,
                13,
                38,
                3
            )
        )
    }

    @Test
    fun valid_numeric1() {
        val postDateTime = "2022.04.24 11:00"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2022,
                4,
                24,
                11,
                0,
                0,
                0
            )
        )
    }

    @Test
    fun valid_numeric2() {
        val postDateTime = "2022-07-07 23:45:00"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2022,
                7,
                7,
                23,
                45,
                0,
                0
            )
        )
    }

    @Test
    fun valid_numeric3() {
        // UTC time
        val postDateTime = "2022-07-07T20:45:00Z"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            LocalDateTime.of(
                2022,
                7,
                7,
                23,
                45,
                0,
                0
            )
        )
    }

    @Test
    fun invalid() {
        val postDateTime = "invalid_date"

        val resultDate = AppDTUtils.dateTimeFromStringOrDefault(
            dtDefault = dtDefault,
            dtAsString = postDateTime
        )

        Assertions.assertThat(resultDate).isEqualTo(
            dtDefault.withHour(0)
                .withMinute(0)
                .withSecond(0)
        )
    }
}