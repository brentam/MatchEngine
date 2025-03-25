package com.brentam.coding.match.util

import org.springframework.web.reactive.function.client.WebClient

data class WebClientConfig(val webClient: WebClient, val workersUrl: String, val jobsUrl: String)