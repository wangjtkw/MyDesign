package com.example.mydesign.data.datasource

import androidx.lifecycle.LiveData
import com.example.mydesign.api.API
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.data.bean.entity.MineInfoEntity
import com.example.mydesign.data.bean.MineInfoResponseBean
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.data.bean.entity.UserAccountEntity
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
    ): LiveData<ApiResponse<MyResponse<UserAccountEntity>>> {
        return api.insertUserInfo(headImg, param)
    }

    fun getUserInfo(scope: CoroutineScope, usersId: Int)
            : LiveData<Resource<MineInfoEntity>> {
        return object :
            ScopeDataSource<MyResponse<MineInfoResponseBean>, MineInfoEntity>(scope) {
            override suspend fun loadData(): LiveData<ApiResponse<MyResponse<MineInfoResponseBean>>> {
                return api.getUserInfo(usersId)
            }

            override fun loadFromDb(): LiveData<MineInfoEntity> {
                return db.mineInfoDao().selectById(usersId)
            }

            override fun shouldFetch(data: MineInfoEntity?): Boolean {
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

    fun updateUserInfo(
        usersAccountId: String,
        usersName: String,
        usersSex: String,
        usersBirthday: String,
        usersRole: String,
        usersPhoneNum: String,
        usersWechat: String,
        usersQq: String,
        educationExperiencesEducation: String,
        educationExperiencesSchool: String,
        educationExperiencesEnterDate: String,
        educationExperiencesMajor: String,
    ): LiveData<ApiResponse<MyResponse<Any>>> {
        return api.updateUserInfo(
            usersAccountId,
            usersName,
            usersSex,
            usersBirthday,
            usersRole,
            usersPhoneNum,
            usersWechat,
            usersQq,
            educationExperiencesEducation,
            educationExperiencesSchool,
            educationExperiencesEnterDate,
            educationExperiencesMajor
        )

    }


    fun updateHeadImg(
        headImg: MultipartBody.Part,
        param: HashMap<String, RequestBody>,
    ): LiveData<ApiResponse<MyResponse<Any>>> {
        return api.updateUserInfoHead(headImg, param)
    }

}