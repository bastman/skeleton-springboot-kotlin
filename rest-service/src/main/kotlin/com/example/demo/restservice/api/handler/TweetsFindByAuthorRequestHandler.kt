package com.example.demo.restservice.api.handler

import com.example.demo.restservice.domain.Tweet
import com.example.demo.restservice.domain.TweetService
import org.springframework.stereotype.Component

@Component
class TweetsFindByAuthorRequestHandler(private val tweetService: TweetService) {
    data class Request(val author: String)
    data class Response(val items: List<Tweet>)

    fun handleRequest(request: Request) =
            Response(items = tweetService
                    .findByAuthor(author = request.author)
                    .sortedByDescending { it.createdAt }
                    .toList()
            )
}