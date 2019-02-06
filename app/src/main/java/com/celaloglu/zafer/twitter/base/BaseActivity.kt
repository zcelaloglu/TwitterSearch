package com.celaloglu.zafer.twitter.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.celaloglu.zafer.twitter.TwitterApplication
import com.celaloglu.zafer.twitter.di.component.ActivityComponent
import com.celaloglu.zafer.twitter.di.component.DaggerActivityComponent

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var activityComponent: ActivityComponent

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        activityComponent = DaggerActivityComponent
                .builder()
                .appComponent(TwitterApplication.appComponent)
                .build()
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int
}