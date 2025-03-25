package com.brentam.coding.match.service

import com.brentam.coding.match.exception.UserNotFoundException
import com.brentam.coding.match.service.helper.isAMatch
import com.brentam.coding.match.model.Job
import com.brentam.coding.match.model.Worker
import com.brentam.coding.match.util.WebClientConfig
import com.brentam.coding.match.util.calculateDistanceInKm
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

interface MatchService {
    fun match(workerId: Int): Mono<List<Job>>
}

@Service
class DefaultMatchService(private val webClientConfig: WebClientConfig) : MatchService {
    override fun match(workerId: Int): Mono<List<Job>> {
        val workerEndpoint =
            retrieveFlux(webClientConfig.webClient, webClientConfig.workersUrl, Worker::class.java)
                .filter { it.userId == workerId }
                .toMono() // Get the first matching worker
                .switchIfEmpty(Mono.error(UserNotFoundException(workerId)))

        val jobsEndpoint = retrieveFlux(webClientConfig.webClient, webClientConfig.jobsUrl, Job::class.java)
            .collectList()

        return Mono.zip(workerEndpoint, jobsEndpoint) { worker, jobs ->
            jobs.filter { worker.isAMatch(it) }
                .sortedBy { calculateDistanceInKm(worker, it) }
                .take(3)
        }
    }

    private fun <T> retrieveFlux(reactiveWebClient: WebClient, url: String, elementClass: Class<T>): Flux<T> {
        return reactiveWebClient.get()
            .uri(url)
            .retrieve()
            .bodyToFlux(elementClass)
    }


}