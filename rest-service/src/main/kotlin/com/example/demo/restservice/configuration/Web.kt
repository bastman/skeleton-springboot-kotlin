package com.example.demo.restservice.configuration

import io.undertow.UndertowOptions
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.ShallowEtagHeaderFilter
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
open class HttpCacheConfiguration {
    @Bean
    open fun shallowEtagHeaderFilter(): ShallowEtagHeaderFilter = ShallowEtagHeaderFilter()
}

@Configuration
open class UndertowServerConfiguration {
    @Bean
    open fun embeddedServletContainerFactory(): UndertowEmbeddedServletContainerFactory =
            UndertowEmbeddedServletContainerFactory().apply {
                addBuilderCustomizers(
                        UndertowBuilderCustomizer {
                            builder ->
                            builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                        })
            }
}

@Configuration
open class RouterConfiguration(swaggerConfiguration: SwaggerConfiguration) {
    private val API_ROUTE_SWAGGER_UI = swaggerConfiguration.API_ROUTE_SWAGGER_UI

    @Bean
    open fun forwardIndexPagesToSwaggerDoc(): WebMvcConfigurerAdapter {
        return object : WebMvcConfigurerAdapter() {
            override fun configurePathMatch(configurer: PathMatchConfigurer) {
                configurer.isUseSuffixPatternMatch = false
            }

            override fun addViewControllers(registry: ViewControllerRegistry) {
                registry.addViewController("/").setViewName("redirect:$API_ROUTE_SWAGGER_UI")
                registry.addViewController("/index.html").setViewName("redirect:$API_ROUTE_SWAGGER_UI")
            }
        }
    }
}

