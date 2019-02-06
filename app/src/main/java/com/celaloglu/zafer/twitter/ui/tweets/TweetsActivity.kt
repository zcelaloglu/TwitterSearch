package com.celaloglu.zafer.twitter.ui.tweets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.celaloglu.zafer.twitter.R
import com.celaloglu.zafer.twitter.base.BaseActivity
import com.celaloglu.zafer.twitter.databinding.ActivityTweetsBinding
import com.celaloglu.zafer.twitter.ui.login.LoginActivity
import com.celaloglu.zafer.twitter.util.Constants
import com.celaloglu.zafer.twitter.util.inTransaction

class TweetsActivity : BaseActivity<ActivityTweetsBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = Constants.TWEETS_ACTIVITY_TITLE
        supportFragmentManager.inTransaction {
            val accessToken = intent.getStringExtra(Constants.ACCESS_TOKEN_KEY)
            add(R.id.container, TweetsFragment.newInstance(accessToken))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_tweets
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.select) {
            LoginActivity.start(this)
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun start(context: Context, accessToken: String) {
            Intent(context, TweetsActivity::class.java)
                    .apply { putExtra(Constants.ACCESS_TOKEN_KEY, accessToken) }
                    .also { context.startActivity(it) }
        }
    }
}