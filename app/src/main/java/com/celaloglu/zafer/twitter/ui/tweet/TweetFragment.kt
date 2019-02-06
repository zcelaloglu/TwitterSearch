package com.celaloglu.zafer.twitter.ui.tweet

import android.os.Bundle
import android.view.View
import com.celaloglu.zafer.twitter.R
import com.celaloglu.zafer.twitter.api.response.Tweet
import com.celaloglu.zafer.twitter.base.BaseFragment
import com.celaloglu.zafer.twitter.databinding.FragmentTweetBinding
import com.celaloglu.zafer.twitter.ui.tweets.TweetsActivity

class TweetFragment : BaseFragment<FragmentTweetBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tweet = arguments!!.getParcelable(TWEET) as Tweet
        binding.item = tweet
        (activity as TweetsActivity).supportActionBar?.title = tweet.username
        (activity as TweetsActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tweet
    }

    companion object {

        private const val TWEET: String = "TWEET"

        fun newInstance(tweet: Tweet): TweetFragment {
            return TweetFragment()
                    .apply { arguments = Bundle(1)
                    .apply { putParcelable(TWEET, tweet) } }
        }
    }
}
