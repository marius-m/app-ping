package lt.markmerkk.ping.configs.db

import lt.markmerkk.ping.BuildConfig
import java.io.IOException
import java.util.Properties

data class DBCreds(
    val host: String,
    val relativeUrl: String,
    val driver: String,
    val user: String,
    val pass: String
) {

    val url = "jdbc:mysql://${host}:3306/${relativeUrl}"

    companion object {
        fun fromProps(
            buildConfig: BuildConfig,
            configPath: String
        ): DBCreds {
            return try {
                val props = Properties().apply {
                    load(DBCreds::class.java.classLoader.getResourceAsStream(configPath))
                }
                DBCreds(
                    host = buildConfig.dockerHost,
                    relativeUrl = props.getProperty("db.primary.relativeUrl"),
                    driver = props.getProperty("db.primary.driver"),
                    user = props.getProperty("db.primary.username"),
                    pass = props.getProperty("db.primary.password")
                )
            } catch (e: IOException) {
                throw IllegalStateException("Error reading database properties. Have you set-up creds.properties?", e)
            }
        }
    }
}