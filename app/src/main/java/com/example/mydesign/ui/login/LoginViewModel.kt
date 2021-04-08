package com.example.mydesign.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydesign.data.bean.entity.UserAccountEntity
import com.example.mydesign.data.bean.Resource
import com.example.mydesign.data.datasource.LoginDataSource
import com.example.mydesign.utils.ToastUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginDataSource: LoginDataSource
) : ViewModel() {

    fun login(account: String, password: String): LiveData<Resource<UserAccountEntity>> {
        if (account.isEmpty()) {
            makeToast("账号不能为空！")
        }
        if (password.isEmpty()) {
            makeToast("密码不能为空！")
        }
        return loginDataSource.login(viewModelScope, account, password)
    }

    private fun makeToast(msg: String) {
        viewModelScope.launch(Dispatchers.Main) {
            ToastUtil.makeToast(msg)
        }
    }
}