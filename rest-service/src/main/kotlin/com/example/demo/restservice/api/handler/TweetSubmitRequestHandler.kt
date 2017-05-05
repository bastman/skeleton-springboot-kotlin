package com.example.demo.restservice.api.handler

import com.example.demo.restservice.domain.Tweet
import com.example.demo.restservice.domain.TweetService
import org.springframework.stereotype.Component

@Component
class TweetSubmitRequestHandler(private val tweetService: TweetService) {
    data class Request(val author: String, val message: String)
    data class Response(val id: String)

    fun handleRequest(request: Request): Response {
        val tweet = request.toTweet()
        tweetService.submit(tweet)

        return Response(id = tweet.id)
    }

    fun Request.toTweet(): Tweet = tweetService.create(author = author, message = message)
}


