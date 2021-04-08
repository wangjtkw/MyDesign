package com.example.mydesign.api

import androidx.lifecycle.LiveData
import com.example.mydesign.data.bean.MineInfoResponseBean
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.data.bean.PositionResponseBean
import com.example.mydesign.data.bean.entity.UserAccountEntity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

@JvmSuppressWildcards
interface API {

    @GET("user/register")
    fun employerRegister(
        @Query("employerAccountAccount") employerAccountAccount: String,
        @Query("employerAccountPassword") employerAccountPassword: String
    ): LiveData<ApiResponse<MyResponse<Any>>>

    @GET("user/login")
    fun employerLogin(
        @Query("employerAccountAccount") employerAccountAccount: String,
        @Query("employerAccountPassword") employerAccountPassword: String
    ): LiveData<ApiResponse<MyResponse<UserAccountEntity>>>

    @Multipart
    @POST("user/insert/userInfo")
    fun insertUserInfo(
        @Part headImg: MultipartBody.Part,
        @PartMap params: Map<String, RequestBody>,
    ): LiveData<ApiResponse<MyResponse<UserAccountEntity>>>

    @GET("user/get/userInfo")
    fun getUserInfo(@Query("usersId") usersId: Int): LiveData<ApiResponse<MyResponse<MineInfoResponseBean>>>

    @Multipart
    @POST("user/update/userInfo/headImg")
    fun updateUserInfoHead(
        @Part headImg: MultipartBody.Part,
        @PartMap params: Map<String, RequestBody>,
    ): LiveData<ApiResponse<MyResponse<Any>>>


    @POST("user/update/userInfo")
    fun updateUserInfo(
        @Query("usersAccountId") usersAccountId: String,
        @Query("usersName") usersName: String,
        @Query("usersSex") usersSex: String,
        @Query("usersBirthday") usersBirthday: String,
        @Query("usersRole") usersRole: String,
        @Query("usersPhoneNum") usersPhoneNum: String,
        @Query("usersWechat") usersWechat: String,
        @Query("usersQq") usersQq: String,
        @Query("educationExperiencesEducation") educationExperiencesEducation: String,
        @Query("educationExperiencesSchool") educationExperiencesSchool: String,
        @Query("educationExperiencesEnterDate") educationExperiencesEnterDate: String,
        @Query("educationExperiencesMajor") educationExperiencesMajor: String,
    ): LiveData<ApiResponse<MyResponse<Any>>>

    @POST("user/update/description")
    fun updateDescription(
        @Query("description") description: String,
        @Query("usersId") usersId: Int
    ): LiveData<ApiResponse<MyResponse<Any>>>

    @GET("user/get/jobList")
    fun getJobList(
        @Query("type") type: String? = null
    ): LiveData<ApiResponse<MyResponse<List<PositionResponseBean>>>>

    @GET("user/get/jobDetail")
    fun getJobDetail(
        @Query("positionId") positionId: Int
    ): LiveData<ApiResponse<MyResponse<PositionResponseBean>>>


}