package com.example.mydesign.data.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployerPositionBean(
    @Json(name = "employerAccountId")
    val employerAccountId: Int?,
    @Json(name = "employerPositionCity")
    val employerPositionCity: String?,
    @Json(name = "employerPositionConnectInfo")
    val employerPositionConnectInfo: String?,
    @Json(name = "employerPositionConnectType")
    val employerPositionConnectType: String?,
    @Json(name = "employerPositionContent")
    val employerPositionContent: String?,
    @Json(name = "employerPositionDate")
    val employerPositionDate: String?,
    @Json(name = "employerPositionId")
    val employerPositionId: Int?,
    @Json(name = "employerPositionImg")
    val employerPositionImg: String?,
    @Json(name = "employerPositionIndustry")
    val employerPositionIndustry: String?,
    @Json(name = "employerPositionPersonNum")
    val employerPositionPersonNum: String?,
    @Json(name = "employerPositionPersonRequirements")
    val employerPositionPersonRequirements: String?,
    @Json(name = "employerPositionPlace")
    val employerPositionPlace: String?,
    @Json(name = "employerPositionSalary")
    val employerPositionSalary: String?,
    @Json(name = "employerPositionSettlement")
    val employerPositionSettlement: String?,
    @Json(name = "employerPositionState")
    val employerPositionState: String?,
    @Json(name = "employerPositionTitle")
    val employerPositionTitle: String?,
    @Json(name = "employerPositionWelfare")
    val employerPositionWelfare: String?,
    @Json(name = "positionRequirementId")
    val positionRequirementId: Int?
)