package lt.markmerkk.ping.entities.entries

import lt.markmerkk.ping.entities.requests.FBDeviceRegisterRequest
import lt.markmerkk.ping.utils.TimeProvider
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "DeviceRegisterEntry")
@Table(name = "device_register")
data class DeviceRegisterEntry(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @Column(name = "user_agent") val userAgent: String,
    @Column(name = "device_name") val deviceName: String,
    @Column(name = "token") val token: String,
) {
    companion object {
        fun fromResponse(
            userAgent: String,
            request: FBDeviceRegisterRequest,
        ): DeviceRegisterEntry {
            return DeviceRegisterEntry(
                userAgent = userAgent,
                deviceName = request.deviceName,
                token = request.token,
            )
        }
    }
}
