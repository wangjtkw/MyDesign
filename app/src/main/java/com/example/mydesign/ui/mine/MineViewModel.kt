package com.example.mydesign.ui.mine

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydesign.data.bean.entity.MineInfoEntity
import com.example.mydesign.data.bean.PersonInfo1Bean
import com.example.mydesign.data.bean.entity.RecordsEntity
import com.example.mydesign.data.datasource.MineInfoDataSource
import com.example.mydesign.ui.main.MainActivity
import com.example.mydesign.utils.ToastUtil
import com.example.mydesignapplication.data.bean.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MineViewModel @Inject constructor(
    private val mineInfoDataSource: MineInfoDataSource
) : ViewModel() {
    private val TAG = "MineViewModel"
    val getUserInfoResult = MediatorLiveData<MineInfoEntity>()
    val getAllRecordsResult = MediatorLiveData<List<RecordsEntity>>()
    val allCount = MutableLiveData<Int>()
    val signUpCount = MutableLiveData<Int>()
    val admissionCount = MutableLiveData<Int>()
    val finishCount = MutableLiveData<Int>()


    fun getInfo() {
        val userId = MainActivity.USER_ACCOUNT_BEAN?.usersId
        if (userId == null) {
            makeToast("您还未填写基本信息！")
            return
        }
        val result = mineInfoDataSource.getUserInfo(viewModelScope, userId)
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
                            description.value = users.usersSelfDescription
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

    fun getAllRecords(userAccountId: Int) {
        val result = mineInfoDataSource.getAllRecords(viewModelScope, userAccountId)
        getAllRecordsResult.addSource(result) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        getAllRecordsResult.value = it.data!!
                        allCount.value = it.data!!.size
                        val signUpList = ArrayList<RecordsEntity>()
                        val admissionList = ArrayList<RecordsEntity>()
                        val finishList = ArrayList<RecordsEntity>()
                        it.data.map { record ->
                            when (record.recordStateUser) {
                                "已报名" -> {
                                    signUpList.add(record)
                                }
                                "已录取" -> {
                                    admissionList.add(record)
                                }
                                "已结束" -> {
                                    finishList.add(record)
                                }
                                else -> {
                                    throw Exception("unknown type")
                                }
                            }
                        }
                        signUpCount.value = signUpList.size
                        admissionCount.value = admissionList.size
                        finishCount.value = finishList.size
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