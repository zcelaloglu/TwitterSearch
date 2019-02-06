package com.celaloglu.zafer.twitter.repository

import com.celaloglu.zafer.twitter.api.response.OauthToken
import com.celaloglu.zafer.twitter.api.response.SearchMetadata
import com.celaloglu.zafer.twitter.api.response.TweetStatus
import com.celaloglu.zafer.twitter.api.service.TwitterService
import com.celaloglu.zafer.twitter.util.Constants
import com.celaloglu.zafer.twitter.util.toDeferred
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TwitterRepositoryTest {

    private lateinit var repository: TwitterRepository

    @Mock
    lateinit var twitterService: TwitterService
    @Mock
    lateinit var searchMetadata: SearchMetadata

    @Before
    @Throws
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = TwitterRepository(twitterService)
        searchMetadata = SearchMetadata()
    }

    @Test
    fun should_get_access_token() {
        val accessToken =  OauthToken(Constants.ACCESS_TOKEN_KEY).toDeferred()
        Mockito.`when`(repository.getAccessToken()).thenReturn(accessToken)
    }

    @Test
    fun should_get_tweet_status_response() {
        val tweetStatus = TweetStatus(emptyList(), searchMetadata).toDeferred()
        Mockito.`when`(repository.getTweets(Constants.ACCESS_TOKEN_KEY)).thenReturn(tweetStatus)
    }
}