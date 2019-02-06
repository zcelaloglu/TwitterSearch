package com.celaloglu.zafer.twitter.ui.tweets

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.celaloglu.zafer.twitter.api.response.Tweet
import com.celaloglu.zafer.twitter.api.response.TweetStatus
import com.celaloglu.zafer.twitter.repository.TwitterRepository
import com.celaloglu.zafer.twitter.ui.login.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*
import javax.inject.Inject

class TweetsViewModel @Inject constructor(private var repository: TwitterRepository) : ViewModel() {

    private val status = MutableLiveData<Status>()
    private val tweets = MutableLiveData<List<Tweet>>()

    fun getStatus(): LiveData<Status> {
        return status
    }

    fun getTweetStatus(): LiveData<List<Tweet>> {
        return tweets
    }

    fun obtainAccessToken(accessToken: String) {
        status.value = Status.VISIBLE_PROGRESS

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = repository.getTweets(accessToken).await()
                status.value = Status.INVISIBLE_PROGRESS
                tweets.value = getTweets(response)
            } catch (e: IOException) {
                status.value = Status.INVISIBLE_PROGRESS
                status.value = Status.NO_NETWORK
            }
        }
    }

    private fun getTweets(tweetStatus: TweetStatus): List<Tweet> {

        val tweets = ArrayList<Tweet>()

        for (status in tweetStatus.statuses) {
            val content = status.text
            val username = status.getUserName()
            val createdAt = status.createdAt
            tweets.add(Tweet(username, content, createdAt))
        }
        return tweets
    }
}