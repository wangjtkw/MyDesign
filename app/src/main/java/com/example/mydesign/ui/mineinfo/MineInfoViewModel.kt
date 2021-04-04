package com.example.mydesign.ui.mineinfo

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydesign.api.ApiEmptyResponse
import com.example.mydesign.api.ApiErrorResponse
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.api.ApiSuccessResponse
import com.example.mydesign.data.bean.*
import com.example.mydesign.data.datasource.LoginDataSource
import com.example.mydesign.data.datasource.MineInfoDataSource
import com.example.mydesign.ui.main.MainActivity
import com.example.mydesign.utils.HttpUtil
import com.example.mydesign.utils.ToastUtil
import com.example.mydesignapplication.data.bean.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MineInfoViewModel @Inject constructor(
    private val mineInfoDataSource: MineInfoDataSource,
    private val loginDataSource: LoginDataSource
) : ViewModel() {
    private val TAG = "MineInfoViewModel"
    val insertPersonInfoResult = MediatorLiveData<ApiResponse<MyResponse<UserAccountBean>>>()
    val getUserInfoResult = MediatorLiveData<MineInfoBean>()
    val updateHeadImgResult = MediatorLiveData<ApiResponse<MyResponse<Any>>>()

    fun insertUserInfo(
        accountId: Int,
        callback: () -> Unit
    ) {
        PersonInfo1Bean.apply {
            val headImg = usersImg.value
            val name = usersName.value
            val sex = usersSex.value
            val birthday = usersBirthday.value
            val role = usersRole.value
            val phoneNum = usersPhoneNum.value
            val weChat = usersWechat.value ?: ""
            val qq = usersQq.value ?: ""

            val education = educationExperiencesEducation.value
            val school = educationExperiencesSchool.value
            val enterDate = educationExperiencesEnterDate.value
            val major = educationExperiencesMajor.value
            val httpUtil = HttpUtil()
            httpUtil.apply {
                addParameter("usersAccountId", accountId)
                addParameter("usersName", name!!)
                addParameter("usersSex", sex!!)
                addParameter("usersBirthday", birthday!!)
                addParameter("usersRole", role!!)
                addParameter("usersPhoneNum", phoneNum!!)
                addParameter("usersWechat", weChat)
                addParameter("usersQq", qq)

                addParameter("educationExperiencesEducation", education!!)
                addParameter("educationExperiencesSchool", school!!)
                addParameter("educationExperiencesEnterDate", enterDate!!)
                addParameter("educationExperiencesMajor", major!!)
            }
            val headImgPart = httpUtil.buildFile("headImg", headImg!!.DATA!!)
            viewModelScope.launch(Dispatchers.IO) {
                val result = mineInfoDataSource.insertUserInfo(httpUtil.params, headImgPart)
                launch(Dispatchers.Main) {
                    insertPersonInfoResult.addSource(result) {
                        when (it) {
                            is ApiSuccessResponse -> {
                                if (it.body.code != 200 || it.body.data == null) {
                                    makeToast("发生错误：${it.body.description}")
                                } else {
                                    makeToast("保存成功")
                                    loginDataSource.insertAccount(viewModelScope, it.body.data)
                                    MainActivity.USER_ACCOUNT_BEAN = it.body.data
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
    }

    public fun getInfo(usersId: Int) {
        val result = mineInfoDataSource.getUserInfo(viewModelScope, usersId)
        getUserInfoResult.addSource(result) {
            Log.d(TAG, it.message ?: "")
            when (it.status) {
                Status.SUCCESS -> {
                    getUserInfoResult.value = it.data!!
                    it.data.let { users ->
                        PersonInfo1Bean.apply {
                            usersName.value = users.usersName
                            usersSex.value = users.usersSex
                            usersBirthday.value = users.usersBirthday
                            usersRole.value = users.usersRole
                            usersPhoneNum.value = users.usersPhoneNum
                            usersWechat.value = users.usersWechat
                            usersQq.value = users.usersQq
                        }
                    }
                    it.data.educationExperiences!!.let { exp ->
                        PersonInfo1Bean.apply {
                            educationExperiencesEducation.value = exp.educationExperiencesEducation
                            educationExperiencesSchool.value = exp.educationExperiencesSchool
                            educationExperiencesEnterDate.value = exp.educationExperiencesEnterDate
                            educationExperiencesMajor.value = exp.educationExperiencesMajor
                        }

                    }
                }
            }
        }
    }

    public fun updateHeadImg(usersId: Int) {
        val headImg = PersonInfo1Bean.usersImg.value
        val httpUtil = HttpUtil()
        httpUtil.addParameter("usersIdStr", usersId)
        val headImgPart = httpUtil.buildFile("headImg", headImg!!.DATA)
        viewModelScope.launch(Dispatchers.IO) {
            val result = mineInfoDataSource.updateHeadImg(headImgPart, httpUtil.params)
            launch(Dispatchers.Main) {
                updateHeadImgResult.addSource(result) {
                    when (it) {
                        is ApiSuccessResponse -> {
                            if (it.body.code != 200) {
                                makeToast("发生错误：${it.body.description}")
                            } else {
                                makeToast("上传成功")
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