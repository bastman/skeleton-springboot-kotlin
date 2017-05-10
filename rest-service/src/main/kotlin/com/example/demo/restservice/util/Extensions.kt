package com.example.demo.restservice.util

import org.springframework.boot.SpringApplication
import kotlin.reflect.KClass

// borrowed from: https://github.com/mixitconf/mixit/blob/master/src/main/kotlin/mixit/util/Extensions.kt

// ----------------------
// Spring Boot extensions
// ----------------------

fun runWeb(type: KClass<*>, vararg args: String) = SpringApplication.run(type.java, *args)

