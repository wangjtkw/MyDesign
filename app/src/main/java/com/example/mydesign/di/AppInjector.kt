package com.example.mydesign.di

import com.example.mydesign.MyApplication

object AppInjector {

    fun init(application: MyApplication) {
        DaggerAppComponent.builder().application(application).build().inject(application)
    }
}