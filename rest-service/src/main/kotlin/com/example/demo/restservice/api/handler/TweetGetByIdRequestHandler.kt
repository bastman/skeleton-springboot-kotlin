package com.example.demo.restservice.api.handler

import com.example.demo.restservice.domain.Tweet
import com.example.demo.restservice.domain.TweetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class TweetGetByIdRequestHandler(private val tweetService: TweetService) {
    data class Request(val id: String)
    data class Response(val tweet: Tweet?) {
        fun toEntity(): ResponseEntity<out TweetGetByIdRequestHandler.Response> {
            return when (tweet) {
                null -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(this)
                else -> ResponseEntity.ok(this)
            }
        }
    }

    fun handleRequest(request: Request) = Response(
            tweet = tweetService.get(tweetId = request.id)
    )
}
