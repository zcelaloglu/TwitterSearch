package com.celaloglu.zafer.twitter.di.component

import com.celaloglu.zafer.twitter.di.module.ViewModelModule
import com.celaloglu.zafer.twitter.di.scopes.PerActivity
import com.celaloglu.zafer.twitter.ui.login.LoginActivity
import com.celaloglu.zafer.twitter.ui.tweets.TweetsActivity
import dagger.Component

@PerActivity
@Component(dependencies = [(AppComponent::class)],
        modules = [(ViewModelModule::class)])
interface ActivityComponent {
    fun inject(login: LoginActivity)
    fun inject(login: TweetsActivity)
}