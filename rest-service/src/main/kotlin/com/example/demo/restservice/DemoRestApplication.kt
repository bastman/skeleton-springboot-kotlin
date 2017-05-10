package com.example.demo.restservice

import com.example.demo.logging.AppLogger
import com.example.demo.restservice.domain.Tweet
import com.example.demo.restservice.domain.TweetService
import com.example.demo.restservice.util.runWeb
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import java.time.Instant

@SpringBootApplication
open class DemoRestApplication {
    private val LOGGER = AppLogger.get(DemoRestApplication::class.java)

    @Bean
    open fun init(
            ctx: ConfigurableApplicationContext,
            @Value("\${app.appName}") appName: String,
            tweetService: TweetService
    ) = CommandLineRunner {
        LOGGER.info("=== init $appName - $ctx =====")

        listOf<Tweet>(
                Tweet(id = "1", author = "seb", message = "message 1", createdAt = Instant.now()),
                Tweet(id = "2", author = "seb", message = "message 1", createdAt = Instant.now())
        ).forEach { tweetService.submit(it) }
    }
}

fun main(args: Array<String>) {
    runWeb(DemoRestApplication::class, *args)
}