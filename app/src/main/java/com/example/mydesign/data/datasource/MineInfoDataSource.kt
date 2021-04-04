package com.example.mydesign.data.datasource

import androidx.lifecycle.LiveData
import com.example.mydesign.api.API
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.data.bean.MineInfoBean
import com.example.mydesign.data.bean.MineInfoResponseBean
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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class MineInfoDataSource @Inject constructor(
    private val api: API,
    private val db: UserDB
) {
    fun insertUserInfo(
        param: HashMap<String, RequestBody>,
        headImg: MultipartBody.Part,
    ): LiveData<ApiResponse<MyResponse<UserAccountBean>>> {
        return api.insertUserInfo(headImg, param)
    }

    fun getUserInfo(scope: CoroutineScope, usersId: Int)
            : LiveData<Resource<MineInfoBean>> {
        return object :
            ScopeDataSource<MyResponse<MineInfoResponseBean>, MineInfoBean>(scope) {
            override suspend fun loadData(): LiveData<ApiResponse<MyResponse<MineInfoResponseBean>>> {
                return api.getUserInfo(usersId)
            }

            override fun loadFromDb(): LiveData<MineInfoBean> {
                return db.mineInfoDao().selectById(usersId)
            }

            override fun shouldFetch(data: MineInfoBean?): Boolean {
                return AppHelper.mContext.isConnectedNetwork()
            }

            override suspend fun saveCallResult(item: MyResponse<MineInfoResponseBean>) {
                if (item.code == 200 && item.data != null) {
                    val userInfoBean = item.data.users
                    userInfoBean.educationExperiences = item.data.educationExperiences
                    db.mineInfoDao().insertMineInfoBean(userInfoBean)
                } else {
                    scope.launch(Dispatchers.Main) {
                        ToastUtil.makeToast("数据异常：${item.description}")
                    }
                }
            }
        }.asLiveData()
    }

    fun updateHeadImg(
        headImg: MultipartBody.Part,
        param: HashMap<String, RequestBody>,
    ): LiveData<ApiResponse<MyResponse<Any>>> {
        return api.updateUserInfoHead(headImg, param)
    }

}