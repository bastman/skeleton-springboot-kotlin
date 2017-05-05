package com.example.demo.restservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
open class ClockConfiguration {
    @Bean
    open fun clock(): Clock = Clock.systemUTC()
}