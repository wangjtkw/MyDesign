package com.example.mydesign.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.mydesign.api.API
import com.example.mydesign.constants.BASE_URL
import com.example.mydesign.data.db.UserDB
import com.example.mydesign.utils.LiveDataCallAdapterFactory

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.UnsupportedEncodingException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModel::class])
class AppModel {
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor {
            try {
                Log.e("OkHttp------", it)
            } catch (e: UnsupportedEncodingException) {
                Log.e("OkHttp------", it)
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS) //读取超时
            .writeTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideAPIService(okHttpClient: OkHttpClient): API {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(API::class.java)
    }

    @Singleton
    @Provides
    fun provideEmployerDB(application: Application): UserDB {
        return Room.databaseBuilder(
            application,
            UserDB::class.java,
            "user.db"
        ).fallbackToDestructiveMigration()//数据库更新时删除数据重新创建
            .build()
    }

}