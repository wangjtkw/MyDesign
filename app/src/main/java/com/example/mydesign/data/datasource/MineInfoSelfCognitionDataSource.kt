package com.example.mydesign.data.datasource

import androidx.lifecycle.LiveData
import com.example.mydesign.api.API
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.data.bean.MyResponse
import javax.inject.Inject

class MineInfoSelfCognitionDataSource @Inject constructor(
    private val api: API
) {

    fun updateDescription(
        userId: Int,
        description: String
    ): LiveData<ApiResponse<MyResponse<Any>>> {
        return api.updateDescription(description, userId)
    }
}