package lt.markmerkk.ping.firebase.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class FBErrorResponse(val error: FBError) {
    companion object {
        fun asEmpty(): FBErrorResponse = FBErrorResponse(FBError.asEmpty())
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class FBError(
    @JsonProperty("code") val code: Int = -1,
    @JsonProperty("message") val message: String = "",
    @JsonProperty("errors") val errors: List<FBErrorDetails> = emptyList()
) {
    companion object {
        fun asEmpty(): FBError = FBError()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class FBErrorDetails(
    val message: String = "",
    val domain: String = "",
    val reason: String = ""
)