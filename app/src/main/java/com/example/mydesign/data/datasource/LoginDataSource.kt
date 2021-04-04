package com.example.mydesign.data.datasource

import androidx.lifecycle.LiveData
import com.example.mydesign.api.API
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.data.bean.UserAccountBean
import com.example.mydesign.data.db.UserDB
import com.example.mydesign.ext.isConnectedNetwork
import com.example.mydesign.utils.AppHelper
import com.example.mydesign.utils.ToastUtil
import com.example.mydesign.data.bean.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val api: API,
    private val db: UserDB
) {

    fun login(
        scope: CoroutineScope,
        account: String,
        password: String
    ): LiveData<Resource<UserAccountBean>> {
        return object :
            ScopeDataSource<MyResponse<UserAccountBean>, UserAccountBean>(scope) {
            override suspend fun loadData(): LiveData<ApiResponse<MyResponse<UserAccountBean>>> {
                return api.employerLogin(account, password)
            }

            override fun loadFromDb(): LiveData<UserAccountBean> {
                return db.userAccountDao().selectByAccount(account)
            }

            override fun shouldFetch(data: UserAccountBean?): Boolean {
                return AppHelper.mContext.isConnectedNetwork()
            }

            override suspend fun saveCallResult(item: MyResponse<UserAccountBean>) {
                if (item.code == 200 && item.data != null) {
                    db.userAccountDao().insertUserAccount(item.data)
                } else {
                    scope.launch(Dispatchers.Main) {
                        ToastUtil.makeToast("登录异常：${item.description}")
                    }
                }
            }

        }.asLiveData()
    }

    fun insertAccount(scope: CoroutineScope, userAccountBean: UserAccountBean) {
        scope.launch(Dispatchers.IO) {
            db.userAccountDao().insertUserAccount(userAccountBean)
        }
    }
}