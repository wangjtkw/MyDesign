package com.example.mydesign.ui.login

import androidx.lifecycle.*
import com.example.mydesign.api.ApiEmptyResponse
import com.example.mydesign.api.ApiErrorResponse
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.api.ApiSuccessResponse
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.utils.ToastUtil
import com.example.mydesign.data.datasource.RegisterDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val registerDataSource: RegisterDataSource
) : ViewModel() {
    private val TAG = "RegisterViewModel"
    val registerResult = MediatorLiveData<ApiResponse<MyResponse<Any>>>()


    fun register(
        account: String,
        password: String,
        passwordAgain: String,
        callback: () -> Unit
    ) {
        when {
            account.isEmpty() -> {
                makeToast("账号不能为空")
                return
//                return MutableLiveData<Resource<MyResponse<Any>>>()
            }
            password.isEmpty() -> {
                makeToast("密码不能为空")
                return
//                return MutableLiveData<Resource<MyResponse<Any>>>()
            }
            passwordAgain.isEmpty() -> {
                makeToast("密码重复不能为空")
                return
//                return MutableLiveData<Resource<MyResponse<Any>>>()
            }
            password != passwordAgain -> {
                makeToast("两次输入密码不一致")
                return
//                return MutableLiveData<Resource<MyResponse<Any>>>()
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            val result = registerDataSource.register(
                account,
                password
            )
            launch(Dispatchers.Main) {
                registerResult.addSource(result) {
                    when (it) {
                        is ApiSuccessResponse -> {
                            if (it.body.code != 200) {
                                makeToast("发生错误：${it.body.description}")
                            } else {
                                callback()
                            }
                        }
                        is ApiEmptyResponse -> {
                            makeToast("注册失败！")
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