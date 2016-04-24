package com.catinthedark.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.sql2o.Sql2o
import org.sql2o.quirks.PostgresQuirks
import javax.sql.DataSource

@Configuration
open class DatabaseConfig {
    @Autowired
    lateinit var dataSource: DataSource

    @Bean
    open fun sql2o(): Sql2o {
        val sql = Sql2o(dataSource, PostgresQuirks())
        
        return sql
    }
}