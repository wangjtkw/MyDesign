package com.example.mydesign.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mydesign.common.MyViewModelFactory
import com.example.mydesign.ui.home.HomePageViewModel
import com.example.mydesign.ui.login.LoginViewModel
import com.example.mydesign.ui.login.RegisterViewModel
import com.example.mydesign.ui.mine.MineViewModel
import com.example.mydesign.ui.mineinfo.MineInfoViewModel
import com.example.mydesign.ui.positiondetail.PositionDetailViewModel
import com.example.mydesign.ui.selfcognition.MineInfoSelfCognitionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModel {

//    @Binds
//    @IntoMap
//    @ViewModelKey(PostPageViewModel::class)
//    abstract fun bindPostPageViewModel(postPageViewModel: PostPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MineInfoViewModel::class)
    abstract fun bindMineInfoViewModel(mineInfoViewModel: MineInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MineInfoSelfCognitionViewModel::class)
    abstract fun bindMineInfoSelfCognitionViewModel(mineInfoSelfCognitionViewModel: MineInfoSelfCognitionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MineViewModel::class)
    abstract fun bindMineViewModel(mineViewModel: MineViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel::class)
    abstract fun bindHomePageViewModel(homePageViewModel: HomePageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PositionDetailViewModel::class)
    abstract fun bindPositionDetailViewModel(positionDetailViewModel: PositionDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

}