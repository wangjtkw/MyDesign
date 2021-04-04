package com.example.mydesign.data.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "user_account_bean")
@JsonClass(generateAdapter = true)
data class UserAccountBean(
    @Json(name = "usersAccountAccount")
    val usersAccountAccount: String,
    @PrimaryKey
    @Json(name = "usersAccountId")
    val usersAccountId: Int,
    @Json(name = "usersAccountPassword")
    val usersAccountPassword: String,
    @Json(name = "usersId")
    val usersId: Int?
)