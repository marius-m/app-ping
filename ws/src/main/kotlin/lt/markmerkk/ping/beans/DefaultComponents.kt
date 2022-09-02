package lt.markmerkk.ping.beans

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import lt.markmerkk.ping.AppConsts
import lt.markmerkk.ping.BuildConfig
import lt.markmerkk.ping.RNDProvider
import lt.markmerkk.ping.configs.db.DBCreds
import lt.markmerkk.ping.utils.TimeProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
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
        logger.info("BuildConfig: ${buildConfig}")
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
        logger.info("Connecting to ${dbCreds.url}")
        return dbCreds
    }

    private val logger = LoggerFactory.getLogger(DefaultComponents::class.java)!!
}