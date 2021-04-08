package com.example.mydesign.data.bean

import com.example.mydesign.data.bean.entity.MineInfoEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MineInfoResponseBean(
    val users: MineInfoEntity,
    val educationExperiences: EducationExperienceBean
)