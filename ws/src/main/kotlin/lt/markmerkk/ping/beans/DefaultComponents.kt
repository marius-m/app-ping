package lt.markmerkk.ping.beans

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import lt.markmerkk.ping.AppConsts
import lt.markmerkk.ping.BuildConfig
import lt.markmerkk.ping.RNDProvider
import lt.markmerkk.ping.configs.db.DBCreds
import lt.markmerkk.ping.dao.PingEntryDao
import lt.markmerkk.ping.firebase.entities.FBCreds
import lt.markmerkk.ping.repositories.PingRepository
import lt.markmerkk.ping.utils.TimeProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.ZoneId

@Component
class DefaultComponents {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    open fun provideTimeProvider(): TimeProvider {
        return TimeProvider(
            clock = Clock.system(ZoneId.systemDefault())
        )
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    open fun provideObjectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerModule(KotlinModule())
        }
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    open fun provideBuildConfig(
        // @Value("\${dockerHost}") dockerHost: String?,
    ): BuildConfig {
        val buildConfig = BuildConfig(
            dockerHost = "localhost",
        )
        l.info("BuildConfig: ${buildConfig}")
        return buildConfig
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    open fun provideRndProvider(): RNDProvider {
        return RNDProvider()
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    open fun provideDbCreds(
        buildConfig: BuildConfig,
    ): DBCreds {
        val dbCreds = DBCreds.fromProps(buildConfig, configPath = AppConsts.DB_CREDS_PATH)
        l.info("Connecting to ${dbCreds.url}")
        return dbCreds
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    open fun providePingRepository(
        pingEntryDao: PingEntryDao,
    ): PingRepository {
        return PingRepository(
            pingEntryDao = pingEntryDao,
        )
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    open fun provideFirebaseCreds(): FBCreds {
        return FBCreds(
            configPath = "u3x-sample-firebase-adminsdk-4dutj-36d7a5d013.json"
        )
    }

    private val l = LoggerFactory.getLogger(DefaultComponents::class.java)!!
}