package com.example.mydesign.data.bean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PositionRequirement(
    @Json(name = "positionRequirementAge")
    var positionRequirementAge: String?,
    @Json(name = "positionRequirementEducation")
    var positionRequirementEducation: String?,
    @Json(name = "positionRequirementHeight")
    var positionRequirementHeight: String?,
    @Json(name = "positionRequirementId")
    var positionRequirementId: Int?,
    @Json(name = "positionRequirementSex")
    var positionRequirementSex: String?
)