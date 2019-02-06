package com.celaloglu.zafer.twitter.api.service

import com.celaloglu.zafer.twitter.api.response.OauthToken
import com.celaloglu.zafer.twitter.api.response.TweetStatus
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface TwitterService {

    @POST("oauth2/token")
    @FormUrlEncoded
    fun getAccessToken(@Header("Authorization") authorization: String?,
                       @Field("grant_type") grantType: String): Deferred<OauthToken>

    @GET("1.1/search/tweets.json")
    fun getTweets(@Header("Authorization") accessToken: String,
                  @Query("q") query: String,
                  @Query("count") count: Int): Deferred<TweetStatus>
}
