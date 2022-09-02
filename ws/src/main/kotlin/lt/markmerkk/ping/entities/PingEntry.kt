package lt.markmerkk.ping.entities

import lt.markmerkk.ping.utils.AppDTUtils
import lt.markmerkk.ping.utils.TimeProvider
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "PingEntry")
@Table(name = "ping_entry")
data class PingEntry(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @Column(name = "user_agent") val userAgent: String,
    @Column(name = "coord_lat") val coordLat: Double,
    @Column(name = "coord_long") val coordLong: Double,
    @Column(name = "device_dt_last_ping") val deviceDtLastPing: LocalDateTime,
    @Column(name = "device_dt_current") val deviceDtCurrent: LocalDateTime,
    @Column(name = "dt_current") val dtCurrent: LocalDateTime,
) {
    companion object {
        fun fromResponse(
            timeProvider: TimeProvider,
            userAgent: String,
            response: ContentDetailResponse,
        ): PingEntry {
            val now = timeProvider.now()
            return PingEntry(
                userAgent = userAgent,
                coordLat = response.coordLat,
                coordLong = response.coordLong,
                deviceDtLastPing = AppDTUtils.dateTimeFromStringOrDefault(
                    dtDefault = now,
                    dtAsString = response.dtLastPing,
                ),
                deviceDtCurrent = AppDTUtils.dateTimeFromStringOrDefault(
                    dtDefault = now,
                    dtAsString = response.dtCurrent,
                ),
                dtCurrent = now,
            )
        }
    }
}
