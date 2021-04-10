package com.example.mydesign.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydesign.data.bean.entity.MineInfoEntity
import com.example.mydesign.data.bean.entity.RecordsEntity

@Dao
interface RecordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecordsEntity(recordsEntity: RecordsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecordsEntity(recordsEntityList: List<RecordsEntity>)

    @Query("SELECT * FROM records_entity WHERE usersAccountId = :userAccountId")
    fun selectByUserAccountId(userAccountId: Int): LiveData<List<RecordsEntity>>
}