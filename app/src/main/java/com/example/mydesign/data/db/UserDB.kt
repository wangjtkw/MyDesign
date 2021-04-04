package com.example.mydesign.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydesign.data.bean.EducationExperienceBean
import com.example.mydesign.data.bean.HomePageItemBean
import com.example.mydesign.data.bean.MineInfoBean
import com.example.mydesign.data.bean.UserAccountBean

@Database(
    entities = [
        UserAccountBean::class,
        MineInfoBean::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UserDB : RoomDatabase() {
    abstract fun userAccountDao(): UserAccountDao
    abstract fun mineInfoDao(): MineInfoDao
}