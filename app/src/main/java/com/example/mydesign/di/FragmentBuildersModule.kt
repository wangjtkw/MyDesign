package com.example.mydesign.di

import com.example.mydesign.ui.mine.MineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeConcernFragment(): MineFragment

}