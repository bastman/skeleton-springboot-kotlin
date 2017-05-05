package com.example.demo.restservice

import com.example.demo.logging.AppLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class DemoRestApplication {
    private val LOGGER = AppLogger.get(DemoRestApplication::class.java)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplicationBuilder()
                    .sources(DemoRestApplication::class.java)
                    .web(true)
                    .run(*args)
        }
    }

    @Bean
    open fun init(
            ctx: ConfigurableApplicationContext,
            @Value("\${app.appName}") appName: String
    ) = CommandLineRunner {
        LOGGER.info("=== init $appName - $ctx =====")
    }
}