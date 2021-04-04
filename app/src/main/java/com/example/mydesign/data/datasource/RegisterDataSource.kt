package com.example.mydesign.data.datasource

import androidx.lifecycle.LiveData
import com.example.mydesign.api.API
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.data.bean.MyResponse
import javax.inject.Inject

class RegisterDataSource @Inject constructor(private val api: API) {

    fun register(
        account: String,
        password: String
    ): LiveData<ApiResponse<MyResponse<Any>>> {
        return api.employerRegister(account,password)
    }

}