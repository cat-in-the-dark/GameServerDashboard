package com.catinthedark

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class GameServerDashboardApplication

fun main(args: Array<String>) {
    SpringApplication.run(GameServerDashboardApplication::class.java, *args)
}
