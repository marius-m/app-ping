package lt.markmerkk.ping.configs.db

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class DBConfig(
    @Autowired private val dbCreds: DBCreds
) {

    @PostConstruct
    fun onCreate() {
       val flyway = Flyway
               .configure()
               .dataSource(
                       dbCreds.url,
                       dbCreds.user,
                       dbCreds.pass
               ).load()
       flyway.migrate()
    }
}