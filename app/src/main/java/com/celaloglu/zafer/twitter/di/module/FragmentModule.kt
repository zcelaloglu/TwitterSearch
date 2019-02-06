package com.celaloglu.zafer.twitter.di.module

import com.celaloglu.zafer.twitter.ui.tweets.TweetsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeTweetsFragment(): TweetsFragment
}