package com.example.mydesign.data.bean

import androidx.lifecycle.MutableLiveData

object PersonInfo1Bean {
    var usersImg = MutableLiveData<AlbumBean>()
    var usersName = MutableLiveData<String>("青团子")
    var usersSex = MutableLiveData<String>("请选择")
    var usersBirthday = MutableLiveData<String>("请选择")
    var usersRole = MutableLiveData<String>("请选择")
    var usersPhoneNum = MutableLiveData<String>()
    var usersWechat = MutableLiveData<String>()
    var usersQq = MutableLiveData<String>()

    var isSelectEducation = false
    var educationExperiencesEducation = MutableLiveData<String>("请选择")
    var educationExperiencesSchool = MutableLiveData<String>()
    var educationExperiencesEnterDate = MutableLiveData<String>("请选择")
    var educationExperiencesMajor = MutableLiveData<String>()

    var description = MutableLiveData<String>()

    fun clear() {
        usersName.value = ""
        usersSex.value = ""
        usersBirthday.value = ""
        usersRole.value = ""
        usersPhoneNum.value = ""
        usersWechat.value = ""
        usersQq.value = ""

        isSelectEducation = false

        educationExperiencesEducation.value = "请选择"
        educationExperiencesSchool.value = ""
        educationExperiencesEnterDate.value = "请选择"
        educationExperiencesMajor.value = ""

    }


}