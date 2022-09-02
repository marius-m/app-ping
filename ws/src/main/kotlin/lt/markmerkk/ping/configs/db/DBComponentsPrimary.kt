package lt.markmerkk.ping.configs.db

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
open class DBComponentsPrimary(
    @Autowired private val dbCreds: DBCreds,
) {

    @Bean
    @Primary
    open fun dataSourcePrimary(): DataSource {
        val dataSource = DataSourceBuilder
            .create()
            .type(HikariDataSource::class.java)
            .url(dbCreds.url)
            .username(dbCreds.user)
            .password(dbCreds.pass)
            .build()
        return dataSource
    }
}
