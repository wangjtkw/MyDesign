package com.example.mydesign.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydesign.data.bean.EducationExperienceBean
import com.example.mydesign.data.bean.MineInfoBean

//@Dao
//interface EducationExperienceDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertEducationExperienceBean(educationExperienceBean: EducationExperienceBean)
//
//    @Query("SELECT * FROM education_experience_bean WHERE educationExperiencesId = :educationId")
//    fun selectById(educationId: Int): LiveData<EducationExperienceBean>
//}