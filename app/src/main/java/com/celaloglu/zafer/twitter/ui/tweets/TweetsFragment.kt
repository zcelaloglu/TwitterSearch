package com.celaloglu.zafer.twitter.ui.tweets

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.celaloglu.zafer.twitter.R
import com.celaloglu.zafer.twitter.api.response.Tweet
import com.celaloglu.zafer.twitter.base.BaseFragment
import com.celaloglu.zafer.twitter.databinding.FragmentTweetsBinding
import com.celaloglu.zafer.twitter.ui.login.Status
import com.celaloglu.zafer.twitter.ui.tweet.TweetFragment
import com.celaloglu.zafer.twitter.util.Constants
import com.celaloglu.zafer.twitter.util.inTransaction
import javax.inject.Inject

class TweetsFragment : BaseFragment<FragmentTweetsBinding>() {

    @Inject
    internal lateinit var viewModel: TweetsViewModel

    private lateinit var adapter: TweetsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent.inject(this)
        val accessToken = arguments!!.getString(Constants.ACCESS_TOKEN_KEY)
        observeStatus()
        observeTweetStatus()
        viewModel.obtainAccessToken(accessToken!!)
        (activity as TweetsActivity).supportActionBar?.title = Constants.TWEETS_ACTIVITY_TITLE
        (activity as TweetsActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tweets
    }

    private fun observeStatus() {
        viewModel.getStatus().observe(this, Observer<Status> {
            when (it) {
                Status.VISIBLE_PROGRESS -> binding.progressBar.visibility = View.VISIBLE
                Status.INVISIBLE_PROGRESS -> binding.progressBar.visibility = View.GONE
                Status.NO_NETWORK -> Toast.makeText(activity?.baseContext,
                        R.string.no_internet_connection_message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun observeTweetStatus() {
        viewModel.getTweetStatus()
                .observe(this, Observer<List<Tweet>> { it ->
                    it.let {
                        adapter = TweetsAdapter(it!!, this)
                        binding.recyclerView.adapter = adapter
                    }
                })
    }

    fun onTweetClick(tweet: Tweet) {
        activity?.supportFragmentManager?.inTransaction {
            this.addToBackStack(TweetFragment::class.java.simpleName)
            replace(R.id.container, TweetFragment.newInstance(tweet))
        }
    }

    companion object {

        fun newInstance(accessToken: String): TweetsFragment {
            return TweetsFragment()
                    .apply {
                        arguments = Bundle(1)
                                .apply { putString(Constants.ACCESS_TOKEN_KEY, accessToken) }
                    }
        }
    }
}