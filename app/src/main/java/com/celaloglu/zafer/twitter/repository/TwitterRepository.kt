package com.celaloglu.zafer.twitter.repository

import com.celaloglu.zafer.twitter.api.response.OauthToken
import com.celaloglu.zafer.twitter.api.response.TweetStatus
import com.celaloglu.zafer.twitter.api.service.TwitterService
import com.celaloglu.zafer.twitter.util.Constants
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class TwitterRepository @Inject constructor(private val service: TwitterService) {

    fun getAccessToken(): Deferred<OauthToken> {
        return service.getAccessToken(Constants.authorizationHeader, Constants.GRANT_TYPE)
    }

    fun getTweets(accessToken: String): Deferred<TweetStatus> {
        return service.getTweets(accessToken, Constants.QUERY, Constants.TWEET_COUNT)
    }
}