package com.example.mydesign.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydesign.data.bean.entity.JobPositionEntity
import com.example.mydesign.data.bean.entity.MineInfoEntity

@Dao
interface JobPositionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobPositionEntity(jobPositionEntity: JobPositionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobPositionEntity(jobPositionEntity: List<JobPositionEntity>)

    @Query("SELECT * FROM job_position_entity")
    fun selectAll(): LiveData<List<JobPositionEntity>>

    @Query("SELECT * FROM job_position_entity WHERE employerPositionIndustry = :type")
    fun selectAll(type: String): LiveData<List<JobPositionEntity>>

    @Query("SELECT * FROM job_position_entity WHERE employerPositionId = :positionId")
    fun selectById(positionId: Int): LiveData<JobPositionEntity>

}