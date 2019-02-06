package com.celaloglu.zafer.twitter.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.celaloglu.zafer.twitter.api.response.OauthToken
import com.celaloglu.zafer.twitter.repository.TwitterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class LoginViewModel @Inject constructor(private var repository: TwitterRepository) : ViewModel() {

    private val status = MutableLiveData<Status>()
    private val accessToken = MutableLiveData<OauthToken>()

    fun getStatus(): LiveData<Status> {
        return status
    }

    fun getAccessToken(): LiveData<OauthToken> {
        return accessToken
    }

    fun obtainAccessToken() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                status.value = Status.VISIBLE_PROGRESS
                val response = repository.getAccessToken().await()
                status.value = Status.INVISIBLE_PROGRESS
                accessToken.value = response
            } catch (e: IOException) {
                status.value = Status.INVISIBLE_PROGRESS
                status.value = Status.NO_NETWORK
            }
        }
    }
}