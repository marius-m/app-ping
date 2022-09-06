package lt.markmerkk.ping.beans

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import lt.markmerkk.ping.AppConsts
import lt.markmerkk.ping.BuildConfig
import lt.markmerkk.ping.RNDProvider
import lt.markmerkk.ping.configs.db.DBCreds
import lt.markmerkk.ping.dao.DeviceRegisterEntryDao
import lt.markmerkk.ping.dao.PingEntryDao
import lt.markmerkk.ping.firebase.entities.FBCreds
import lt.markmerkk.ping.repositories.FirebaseRepository
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
        return ObjectMapper()
            .registerKotlinModule()
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
    open fun provideDeviceRegisterRepository(
        deviceRegisterEntryDao: DeviceRegisterEntryDao,
    ): FirebaseRepository {
        return FirebaseRepository(
            deviceRegisterDao = deviceRegisterEntryDao,
        )
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    open fun provideFirebaseCreds(): FBCreds {
        return FBCreds(
            configPath = "u3x-sample-firebase-adminsdk-4dutj-e11d08be9a.json"
        )
    }

    private val l = LoggerFactory.getLogger(DefaultComponents::class.java)!!
}