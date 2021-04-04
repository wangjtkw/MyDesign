package com.example.mydesign.data.bean

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "mine_info_bean")
@JsonClass(generateAdapter = true)
data class MineInfoBean(
    @Json(name = "curriculumVitaeId")
    val curriculumVitaeId: Int?,
    @Json(name = "educationExperiencesId")
    val educationExperiencesId: Int?,
    @Json(name = "userPersonalTagId")
    val userPersonalTagId: Int?,
    @Json(name = "usersBirthday")
    val usersBirthday: String?,
    @PrimaryKey
    @Json(name = "usersId")
    val usersId: Int?,
    @Json(name = "usersImg")
    val usersImg: String?,
    @Json(name = "usersName")
    val usersName: String?,
    @Json(name = "usersPhoneNum")
    val usersPhoneNum: String?,
    @Json(name = "usersQq")
    val usersQq: String?,
    @Json(name = "usersRole")
    val usersRole: String?,
    @Json(name = "usersSelfDescription")
    val usersSelfDescription: String?,
    @Json(name = "usersSex")
    val usersSex: String?,
    @Json(name = "usersWechat")
    val usersWechat: String?,
    @Embedded
    var educationExperiences: EducationExperienceBean?
){
    override fun toString(): String {
        return "MineInfoBean(curriculumVitaeId=$curriculumVitaeId, educationExperiencesId=$educationExperiencesId, userPersonalTagId=$userPersonalTagId, usersBirthday=$usersBirthday, usersId=$usersId, usersImg=$usersImg, usersName=$usersName, usersPhoneNum=$usersPhoneNum, usersQq=$usersQq, usersRole=$usersRole, usersSelfDescription=$usersSelfDescription, usersSex=$usersSex, usersWechat=$usersWechat, educationExperiences=$educationExperiences)"
    }
}