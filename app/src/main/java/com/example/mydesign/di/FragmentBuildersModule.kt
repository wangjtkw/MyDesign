package com.example.mydesign.di

import com.example.mydesign.ui.home.HomePageFragment
import com.example.mydesign.ui.mine.MineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMineFragment(): MineFragment

    @ContributesAndroidInjector
    abstract fun contributeHomePageFragment(): HomePageFragment

}