package com.celaloglu.zafer.twitter.di.component

import com.celaloglu.zafer.twitter.di.module.ViewModelModule
import com.celaloglu.zafer.twitter.di.scopes.PerFragment
import com.celaloglu.zafer.twitter.ui.tweets.TweetsFragment
import dagger.Component

@PerFragment
@Component(dependencies = [(AppComponent::class)],
        modules = [(ViewModelModule::class)])
interface FragmentComponent {
    fun inject(fragment: TweetsFragment)
}