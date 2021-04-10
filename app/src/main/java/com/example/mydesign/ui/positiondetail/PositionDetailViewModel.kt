package com.example.mydesign.ui.positiondetail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydesign.api.ApiEmptyResponse
import com.example.mydesign.api.ApiErrorResponse
import com.example.mydesign.api.ApiSuccessResponse
import com.example.mydesign.data.bean.entity.RecordsEntity
import com.example.mydesign.data.datasource.PositionDetailDataSource
import com.example.mydesign.data.datasource.RecordsDataSource
import com.example.mydesign.utils.ToastUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PositionDetailViewModel @Inject constructor(
    private val positionDetailDataSource: PositionDetailDataSource,
    private val recordsDataSource: RecordsDataSource
) : ViewModel() {

    val signUpResult = MediatorLiveData<RecordsEntity>()

    fun getJobDetail(positionId: Int) =
        positionDetailDataSource.getDetail(viewModelScope, positionId)

    fun signUp(
        userAccountId: Int,
        jobId: Int,
        employerAccountId: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = recordsDataSource.signUp(userAccountId, jobId, employerAccountId)
            launch(Dispatchers.Main) {
                signUpResult.addSource(result) {
                    when (it) {
                        is ApiSuccessResponse -> {
                            if (it.body.code == 1001) {
                                makeToast("您已经报名！")
                            } else if (it.body.code != 200 || it.body.data == null) {
                                makeToast("发生错误：${it.body.description}")
                            } else {
                                makeToast("报名成功")
                                recordsDataSource.saveRecordsDB(viewModelScope, it.body.data)
                            }
                        }
                        is ApiEmptyResponse -> {
                            makeToast("发生错误：")
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