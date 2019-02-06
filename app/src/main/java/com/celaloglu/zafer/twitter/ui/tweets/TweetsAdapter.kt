package com.celaloglu.zafer.twitter.ui.tweets

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.celaloglu.zafer.twitter.api.response.Tweet
import com.celaloglu.zafer.twitter.databinding.ItemTweetBinding
import java.lang.ref.WeakReference

class TweetsAdapter(private val tweets: List<Tweet>, fragment: TweetsFragment) :
        RecyclerView.Adapter<TweetsAdapter.TweetViewHolder>() {

    private val fragment = WeakReference(fragment)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTweetBinding.inflate(inflater, parent, false)
        return TweetViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.bind(tweet)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    class TweetViewHolder(private val binding: ViewDataBinding,
                          private val fragment: WeakReference<TweetsFragment>)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Tweet) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.view, fragment.get())
            binding.executePendingBindings()
        }
    }
}