package com.example.mydesign.data.bean

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MineInfoResponseBean(
    val users: MineInfoBean,
    val educationExperiences: EducationExperienceBean
)