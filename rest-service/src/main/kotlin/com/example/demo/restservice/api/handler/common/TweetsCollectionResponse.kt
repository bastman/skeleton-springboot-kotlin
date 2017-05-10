package com.example.demo.restservice.api.handler.common

import com.example.demo.restservice.domain.Tweet

data class TweetsCollectionResponse(val tweets:List<Tweet>) {
    companion object {
        fun of(tweets: Sequence<Tweet>) = TweetsCollectionResponse(tweets = tweets.toList())
    }
}
