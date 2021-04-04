package com.example.mydesign.api

import androidx.lifecycle.LiveData
import com.example.mydesign.data.bean.MineInfoResponseBean
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.data.bean.UserAccountBean
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
    ): LiveData<ApiResponse<MyResponse<UserAccountBean>>>

    @Multipart
    @POST("user/insert/userInfo")
    fun insertUserInfo(
        @Part headImg: MultipartBody.Part,
        @PartMap params: Map<String, RequestBody>,
    ): LiveData<ApiResponse<MyResponse<UserAccountBean>>>

    @GET("user/get/userInfo")
    fun getUserInfo(@Query("usersId") usersId: Int): LiveData<ApiResponse<MyResponse<MineInfoResponseBean>>>

    @Multipart
    @POST("user/update/userInfo/headImg")
    fun updateUserInfoHead(
        @Part headImg: MultipartBody.Part,
        @PartMap params: Map<String, RequestBody>,
    ): LiveData<ApiResponse<MyResponse<Any>>>
}