package lt.markmerkk.ping.entities

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
)
