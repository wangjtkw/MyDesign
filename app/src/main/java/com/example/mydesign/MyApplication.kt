package com.example.mydesign

import android.app.Application
import com.example.mydesign.utils.AppHelper
import com.example.mydesign.di.AppInjector

import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        AppHelper.init(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}