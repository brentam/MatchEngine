package com.brentam.coding.match.config

import com.brentam.coding.match.util.WebClientConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Config(private val env: Environment) {

    @Bean
    fun webClient(): WebClientConfig {
        val workersUrl = env.getProperty("web-service.configs.worker-url")!!
        val jobsUrl = env.getProperty("web-service.configs.job-url")!!
        return WebClientConfig(WebClient.builder().build(), workersUrl, jobsUrl)
    }
}