package lt.markmerkk.ping.entities.requests

data class FBDeviceRegisterRequest(
    val deviceName: String,
    val token: String,
)