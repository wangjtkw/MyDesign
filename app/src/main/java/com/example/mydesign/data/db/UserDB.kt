package com.example.mydesign.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydesign.data.bean.entity.JobPositionEntity
import com.example.mydesign.data.bean.entity.MineInfoEntity
import com.example.mydesign.data.bean.entity.RecordsEntity
import com.example.mydesign.data.bean.entity.UserAccountEntity

@Database(
    entities = [
        UserAccountEntity::class,
        MineInfoEntity::class,
        JobPositionEntity::class,
        RecordsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UserDB : RoomDatabase() {
    abstract fun userAccountDao(): UserAccountDao
    abstract fun mineInfoDao(): MineInfoDao
    abstract fun jobPositionDao(): JobPositionDao
    abstract fun recordsDao(): RecordsDao
}