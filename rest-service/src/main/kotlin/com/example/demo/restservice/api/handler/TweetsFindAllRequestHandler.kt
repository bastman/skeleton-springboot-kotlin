package com.example.demo.restservice.api.handler

import com.example.demo.restservice.api.handler.common.TweetsCollectionResponse
import com.example.demo.restservice.domain.TweetService
import org.springframework.stereotype.Component

@Component
class TweetsFindAllRequestHandler(private val tweetService: TweetService) {
    fun handleRequest() = TweetsCollectionResponse.of(
            tweetService.getItems()
                    .sortedByDescending { it.createdAt }
    )
}