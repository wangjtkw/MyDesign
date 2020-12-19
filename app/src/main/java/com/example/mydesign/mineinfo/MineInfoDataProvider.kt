package com.example.mydesign.mineinfo

import com.example.mydesign.bean.EducationExperience
import com.example.mydesign.bean.MineInfoBean

class MineInfoDataProvider {

    fun provide(): MineInfoBean {
        val educationExperience = EducationExperience("本科", "重庆邮电大学", "2017", "软件工程专业")
        val mineInfo = MineInfoBean(
            "null",
            "王杰",
            "男",
            "1998-07-03",
            "学生党",
            "18223490120",
            "",
            "",
            educationExperience,
        )
        return mineInfo
    }
}