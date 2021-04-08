package com.example.mydesign.data.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PositionResponseBean(
    @Json(name = "companyInfo")
    var companyInfo: CompanyInfoBean?,
    @Json(name = "position")
    var position: EmployerPositionBean?,
    @Json(name = "positionRequirement")
    var positionRequirement: PositionRequirement?
)

