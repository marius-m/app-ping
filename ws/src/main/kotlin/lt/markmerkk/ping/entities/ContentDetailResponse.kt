package lt.markmerkk.ping.entities

data class ContentDetailResponse(
    val coordLat: Double,
    val coordLong: Double,
    val dtLastPing: String,
    val dtCurrent: String,
    val extras: String,
)