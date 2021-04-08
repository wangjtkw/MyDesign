package com.example.mydesign.di

import androidx.paging.ExperimentalPagingApi
import com.example.mydesign.ui.login.LoginActivity
import com.example.mydesign.ui.login.RegisterActivity
import com.example.mydesign.ui.main.MainActivity
import com.example.mydesign.ui.mineinfo.MineInfoActivity
import com.example.mydesign.ui.positiondetail.PositionDetailActivity
import com.example.mydesign.ui.selfcognition.MineInfoSelfCognitionActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModel {

    @ExperimentalPagingApi
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ExperimentalPagingApi
    @ContributesAndroidInjector()
    abstract fun contributeLoginActivity(): LoginActivity

    @ExperimentalPagingApi
    @ContributesAndroidInjector()
    abstract fun contributeRegisterActivity(): RegisterActivity

    @ExperimentalPagingApi
    @ContributesAndroidInjector()
    abstract fun contributeMineInfoActivity(): MineInfoActivity

    @ExperimentalPagingApi
    @ContributesAndroidInjector()
    abstract fun contributeMineInfoSelfCognitionActivity(): MineInfoSelfCognitionActivity

    @ExperimentalPagingApi
    @ContributesAndroidInjector()
    abstract fun contributePositionDetailActivity(): PositionDetailActivity

}