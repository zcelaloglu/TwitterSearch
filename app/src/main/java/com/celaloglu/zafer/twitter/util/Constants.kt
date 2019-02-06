package com.celaloglu.zafer.twitter.util

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets

object Constants {

    private const val CONSUMER_KEY = "NpYC6fAdQKMiPt8FNgpvqgLHh"
    private const val CONSUMER_SECRET = "owcdhUqvtxN64nhvrrL1YVIldw1GXbl3AmQy0hzO1Axo7Zl9Sd"
    const val GRANT_TYPE = "client_credentials"
    const val ACCESS_TOKEN_KEY = "access_token"
    const val TWEETS_ACTIVITY_TITLE = "Tweets"
    const val LOGIN_ACTIVITY_TITLE = "Login"
    const val TWEET_COUNT = 100
    const val QUERY = "turkey"

    val authorizationHeader: String?
        get() {
            return try {
                val consumerKeyAndSecret = "$CONSUMER_KEY:$CONSUMER_SECRET"
                val data = consumerKeyAndSecret.toByteArray(charset(StandardCharsets.UTF_8.name()))
                val base64 = Base64.encodeToString(data, Base64.NO_WRAP)
                "Basic $base64"
            } catch (e: UnsupportedEncodingException) {
                null
            }
        }
}
