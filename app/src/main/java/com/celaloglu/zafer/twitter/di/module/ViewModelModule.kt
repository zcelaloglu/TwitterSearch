package com.celaloglu.zafer.twitter.di.module

import android.arch.lifecycle.ViewModel
import com.celaloglu.zafer.twitter.di.scopes.ViewModelKey
import com.celaloglu.zafer.twitter.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(loginViewModel: LoginViewModel): ViewModel
}

