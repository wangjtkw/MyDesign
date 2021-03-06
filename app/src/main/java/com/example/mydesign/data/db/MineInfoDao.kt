package com.example.mydesign.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydesign.data.bean.entity.MineInfoEntity

@Dao
interface MineInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMineInfoBean(mineInfoBean: MineInfoEntity)

    @Query("SELECT * FROM mine_info_bean WHERE usersId = :usersId")
    fun selectById(usersId: Int): LiveData<MineInfoEntity>
}