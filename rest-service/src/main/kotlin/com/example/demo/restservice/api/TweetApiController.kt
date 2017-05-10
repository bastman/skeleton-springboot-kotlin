package com.example.demo.restservice.api

import com.example.demo.restservice.api.handler.TweetGetByIdRequestHandler
import com.example.demo.restservice.api.handler.TweetSubmitRequestHandler
import com.example.demo.restservice.api.handler.TweetsFindAllRequestHandler
import com.example.demo.restservice.api.handler.TweetsFindByAuthorRequestHandler
import com.example.demo.restservice.api.handler.common.TweetsCollectionResponse
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("*"))
open class TweetApiController(
        private val tweetSubmitRequestHandler: TweetSubmitRequestHandler,
        private val tweetGetByIdRequestHandler: TweetGetByIdRequestHandler,
        private val tweetsFindAllRequestHandler: TweetsFindAllRequestHandler,
        private val tweetsFindByAuthorRequestHandler: TweetsFindByAuthorRequestHandler
) {

    object ApiRequestFields {
        const val TWEET_ID = "id"
        const val AUTHOR = "author"
    }

    object ApiRoutes {
        const val TWEET_SUBMIT = "/api/tweet/submit"
        const val TWEET_GET_BY_ID = "/api/tweet/{${ApiRequestFields.TWEET_ID}}"
        const val TWEETS_FIND_ALL = "/api/tweet"
        const val TWEETS_FIND_BY_AUTHOR = "/api/author/{${ApiRequestFields.AUTHOR}}/tweets"
    }

    @RequestMapping(
            value = ApiRoutes.TWEET_SUBMIT,
            method = arrayOf(RequestMethod.POST),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
    )
    @ApiOperation(
            value = "submit a tweet",
            notes = "This adds a tweet to local in-memory store and returns a tweet id.",
            response = TweetSubmitRequestHandler.Response::class
    )
    fun submitTweet(
            @RequestBody request: TweetSubmitRequestHandler.Request
    ) = tweetSubmitRequestHandler.handleRequest(request)

    @RequestMapping(
            value = ApiRoutes.TWEET_GET_BY_ID,
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
    )
    @ApiOperation(
            value = "get tweet by id",
            notes = "returns nothing if not found.",
            response = TweetGetByIdRequestHandler.Response::class
    )
    fun getTweetById(
            @PathVariable(name = ApiRequestFields.TWEET_ID) tweetId: String
    ): ResponseEntity<out TweetGetByIdRequestHandler.Response> {
        val request = TweetGetByIdRequestHandler.Request(id = tweetId)
        val response = tweetGetByIdRequestHandler
                .handleRequest(request)

        return response.toEntity()
    }

    @RequestMapping(
            value = ApiRoutes.TWEETS_FIND_ALL,
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
    )
    @ApiOperation(
            value = "find all tweets",
            notes = "returns empty collection if nothing found.",
            response = TweetsCollectionResponse::class
    )
    fun findAllTweets() = tweetsFindAllRequestHandler.handleRequest()

    @RequestMapping(
            value = ApiRoutes.TWEETS_FIND_BY_AUTHOR,
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
    )
    @ApiOperation(
            value = "find all tweets by author",
            notes = "returns empty collection if nothing found.",
            response = TweetsCollectionResponse::class
    )
    fun findTweetsByAuthor(
            @PathVariable(name = ApiRequestFields.AUTHOR) author: String
    ) = tweetsFindByAuthorRequestHandler.handleRequest(
            request = TweetsFindByAuthorRequestHandler.Request(author = author)
    )
}