package lt.markmerkk.ping.entities.requests

data class ContentDetailRequest(
    val coordLat: Double,
    val coordLong: Double,
    val dtLastPing: String,
    val dtCurrent: String,
    val extras: String,
)