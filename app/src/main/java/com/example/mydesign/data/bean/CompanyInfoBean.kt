package com.example.mydesign.data.bean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyInfoBean(
    @Json(name = "employerCompanyInfoAuditState")
    var employerCompanyInfoAuditState: String?,
    @Json(name = "employerCompanyInfoName")
    var employerCompanyInfoName: String?,
    @Json(name = "employerCompanyInfoBusinessScope")
    var employerCompanyInfoBusinessScope: String?,
    @Json(name = "employerCompanyInfoBusinessState")
    var employerCompanyInfoBusinessState: String?,
    @Json(name = "employerCompanyInfoCompanyType")
    var employerCompanyInfoCompanyType: String?,
    @Json(name = "employerCompanyInfoFoundTime")
    var employerCompanyInfoFoundTime: String?,
    @Json(name = "employerCompanyInfoId")
    var employerCompanyInfoId: Int?,
    @Json(name = "employerCompanyInfoOrganizationCode")
    var employerCompanyInfoOrganizationCode: String?,
    @Json(name = "employerCompanyInfoRegisterAddress")
    var employerCompanyInfoRegisterAddress: String?,
    @Json(name = "employerCompanyInfoUniformSocialCreditCode")
    var employerCompanyInfoUniformSocialCreditCode: String?
)