package com.celaloglu.zafer.twitter.ui.login

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.celaloglu.zafer.twitter.R
import com.celaloglu.zafer.twitter.api.response.OauthToken
import com.celaloglu.zafer.twitter.base.BaseActivity
import com.celaloglu.zafer.twitter.databinding.ActivityLoginBinding
import com.celaloglu.zafer.twitter.ui.tweets.TweetsActivity
import com.celaloglu.zafer.twitter.util.Constants
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    @Inject
    internal lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        supportActionBar?.title = Constants.LOGIN_ACTIVITY_TITLE
        binding.vm = viewModel
        observeStatus()
        observeAccessToken()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    private fun observeStatus() {
        viewModel.getStatus().observe(this, Observer<Status> {
            when (it) {
                Status.VISIBLE_PROGRESS -> binding.progressBar.visibility = View.VISIBLE
                Status.INVISIBLE_PROGRESS -> binding.progressBar.visibility = View.GONE
                Status.NO_NETWORK -> Toast.makeText(this,
                        R.string.no_internet_connection_message,
                        Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun observeAccessToken() {
        viewModel.getAccessToken()
                .observe(this, Observer<OauthToken> { it ->
                    it.let {
                        val accessToken = getString(R.string.access_token_prefix) + it!!.accessToken
                        TweetsActivity.start(this, accessToken)
                        finish()
                    }
                })
    }

    companion object {

        fun start(context: Context) {
            Intent(context, LoginActivity::class.java)
                    .also { context.startActivity(it) }
        }
    }
}
