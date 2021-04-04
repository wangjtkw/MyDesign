package com.example.mydesign.data.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EducationExperienceBean(
    @Json(name = "educationExperiencesEducation")
    var educationExperiencesEducation: String?,
    @Json(name = "educationExperiencesEnterDate")
    var educationExperiencesEnterDate: String?,
    @Json(name = "educationExperiencesId")
    var educationId: Int?,
    @Json(name = "educationExperiencesMajor")
    var educationExperiencesMajor: String?,
    @Json(name = "educationExperiencesSchool")
    var educationExperiencesSchool: String?
){
    override fun toString(): String {
        return "EducationExperienceBean(educationExperiencesEducation=$educationExperiencesEducation, educationExperiencesEnterDate=$educationExperiencesEnterDate, educationId=$educationId, educationExperiencesMajor=$educationExperiencesMajor, educationExperiencesSchool=$educationExperiencesSchool)"
    }
}