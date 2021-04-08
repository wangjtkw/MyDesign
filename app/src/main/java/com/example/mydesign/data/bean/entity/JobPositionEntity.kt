package com.example.mydesign.data.bean.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mydesign.data.bean.CompanyInfoBean
import com.example.mydesign.data.bean.PositionRequirement
import com.example.mydesign.data.bean.PositionResponseBean
import com.squareup.moshi.Json

@Entity(tableName = "job_position_entity")
data class JobPositionEntity(
    val employerAccountId: Int?,
    val employerPositionCity: String?,
    val employerPositionConnectInfo: String?,
    val employerPositionConnectType: String?,
    val employerPositionContent: String?,
    val employerPositionDate: String?,
    @PrimaryKey
    val employerPositionId: Int?,
    val employerPositionImg: String?,
    val employerPositionIndustry: String?,
    val employerPositionPersonNum: String?,
    val employerPositionPersonRequirements: String?,
    val employerPositionPlace: String?,
    val employerPositionSalary: String?,
    val employerPositionSettlement: String?,
    val employerPositionState: String?,
    val employerPositionTitle: String?,
    val employerPositionWelfare: String?,
    @Embedded
    var positionRequirement: PositionRequirement?,
    @Embedded
    var companyInfoBean: CompanyInfoBean?
) {
    companion object {
        fun PositionResponseBean2JobPositionEntity(positionResponseBean: PositionResponseBean): JobPositionEntity {
            positionResponseBean.apply {
                val requirement = positionRequirement
                val companyInfoBean = companyInfo
                position!!.apply {
                    return JobPositionEntity(
                        employerAccountId = position!!.employerAccountId,
                        employerPositionCity = position!!.employerPositionCity,
                        employerPositionConnectInfo = position!!.employerPositionConnectInfo,
                        employerPositionConnectType = position!!.employerPositionConnectType,
                        employerPositionContent = position!!.employerPositionContent,
                        employerPositionDate = position!!.employerPositionDate,
                        employerPositionId = position!!.employerPositionId,
                        employerPositionImg = position!!.employerPositionImg,
                        employerPositionIndustry = position!!.employerPositionIndustry,
                        employerPositionPersonNum = position!!.employerPositionPersonNum,
                        employerPositionPersonRequirements = position!!.employerPositionPersonRequirements,
                        employerPositionPlace = position!!.employerPositionPlace,
                        employerPositionSalary = position!!.employerPositionSalary,
                        employerPositionSettlement = position!!.employerPositionSettlement,
                        employerPositionState = position!!.employerPositionState,
                        employerPositionTitle = position!!.employerPositionTitle,
                        employerPositionWelfare = position!!.employerPositionWelfare,
                        positionRequirement = requirement,
                        companyInfoBean = companyInfoBean
                    )
                }

            }
        }
    }
}