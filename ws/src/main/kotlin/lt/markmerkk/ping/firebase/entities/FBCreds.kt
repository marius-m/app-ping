package lt.markmerkk.ping.firebase.entities

import java.io.InputStream

data class FBCreds(
    val configPath: String
) {
    fun configAsStream(): InputStream {
        return FBCreds::class.java.classLoader
            .getResourceAsStream(configPath)!!
    }
}
