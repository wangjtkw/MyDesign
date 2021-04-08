package com.example.mydesign.ui.selfcognition

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydesign.api.ApiEmptyResponse
import com.example.mydesign.api.ApiErrorResponse
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.api.ApiSuccessResponse
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.data.bean.PersonInfo1Bean
import com.example.mydesign.data.datasource.MineInfoSelfCognitionDataSource
import com.example.mydesign.utils.ToastUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MineInfoSelfCognitionViewModel @Inject constructor(
    private val dataSource: MineInfoSelfCognitionDataSource
) : ViewModel() {
    val updateDescriptionResult = MediatorLiveData<ApiResponse<MyResponse<Any>>>()

    fun updateDescription(
        userId: Int,
        callback: () -> Unit
    ) {
        val description = PersonInfo1Bean.description.value
        viewModelScope.launch(Dispatchers.IO) {
            val result = dataSource.updateDescription(userId, description!!)
            launch(Dispatchers.Main) {
                updateDescriptionResult.addSource(result) {
                    when (it) {
                        is ApiSuccessResponse -> {
                            if (it.body.code != 200) {
                                makeToast("发生错误：${it.body.description}")
                            } else {
                                makeToast("上传成功")
                                callback()
                            }
                        }
                        is ApiEmptyResponse -> {
                            makeToast("保存失败！")
                        }
                        is ApiErrorResponse -> {
                            makeToast("发生错误：${it.errorMessage}")
                        }
                        else -> {
                            makeToast("失败！")
                        }
                    }
                }
            }
        }
    }

    private fun makeToast(msg: String) {
        viewModelScope.launch(Dispatchers.Main) {
            ToastUtil.makeToast(msg)
        }
    }
}