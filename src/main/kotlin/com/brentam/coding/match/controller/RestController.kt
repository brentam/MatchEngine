package com.brentam.coding.match.controller

import com.brentam.coding.match.exception.UserNotFoundException
import com.brentam.coding.match.model.Job
import com.brentam.coding.match.service.MatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/")
class RestController(@Autowired val matchService: MatchService) {

    @GetMapping("/matches/{workerId}")
    fun matchesForWorker(@PathVariable workerId: Int): Mono<List<Job>> {
        return matchService.match(workerId = workerId)
            .onErrorMap(UserNotFoundException::class.java) {
                ResponseStatusException(HttpStatus.NOT_FOUND, it.message)
            }
    }
}