package com.example.mydesign.ui.mineinfoeducation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mydesign.R
import com.example.mydesign.data.bean.PersonInfo1Bean
import com.example.mydesign.databinding.ActivityMineInfoEducationBinding
import com.example.mydesign.dialog.MineInfoSingleDataDialogUtil
import com.example.mydesign.ui.mineinfo.MineInfoActivity.Companion.EDUCATION_EXPERIENCE
import com.example.mydesign.ui.mineinfo.MineInfoActivity.Companion.EDUCATION_EXPERIENCE_REQUEST_CODE
import com.example.mydesign.utils.StatusBarUtils
import com.example.mydesign.utils.ToastUtil

/**
 * 教育经历
 */
class MineInfoEducationActivity : AppCompatActivity() {

    private lateinit var educationList: List<String>
    private lateinit var enterDateList: List<String>

    private lateinit var educationSelectCallback: (Int) -> Unit
    private lateinit var enterDateSelectCallback: (Int) -> Unit

    private var mBinding: ActivityMineInfoEducationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mine_info_education)
        init()
    }

    private fun init() {
        initData()
        observeData()
        setListener()
        initListener()
    }

    private fun observeData() {
        PersonInfo1Bean.apply {
            educationExperiencesEducation.observe(this@MineInfoEducationActivity) {
                mBinding!!.activityMineInfoEducationEducationSelectTextView.text = it
            }
            educationExperiencesSchool.observe(this@MineInfoEducationActivity) {
                mBinding!!.activityMineInfoEducationSchoolSelectEditText.setText(it)
            }
            educationExperiencesEnterDate.observe(this@MineInfoEducationActivity) {
                mBinding!!.activityMineInfoEducationEnterDateSelectTextView.text = it
            }
            educationExperiencesMajor.observe(this@MineInfoEducationActivity) {
                mBinding!!.activityMineInfoEducationMajorEditText.setText(it)
            }
        }
    }

    private fun check(): Boolean {
        val education = mBinding!!.activityMineInfoEducationEducationSelectTextView.text.toString()
        val school = mBinding!!.activityMineInfoEducationSchoolSelectEditText.text.toString()
        val enterDate = mBinding!!.activityMineInfoEducationEnterDateSelectTextView.text.toString()
        val major = mBinding!!.activityMineInfoEducationMajorEditText.text.toString()
        if (education.isEmpty() || "请选择" == education) {
            ToastUtil.makeToast("请选择学历！")
            return false
        }
        if (school.isEmpty()) {
            ToastUtil.makeToast("请填写学校！")
            return false
        }
        if (enterDate.isEmpty() || "请选择" == enterDate) {
            ToastUtil.makeToast("请选择入学日期！")
            return false
        }
        if (major.isEmpty()) {
            ToastUtil.makeToast("请填写专业！")
            return false
        }
        PersonInfo1Bean.apply {
            educationExperiencesEducation.value = education
            educationExperiencesSchool.value = school
            educationExperiencesEnterDate.value = enterDate
            educationExperiencesMajor.value = major
            isSelectEducation = true
        }
        return true
    }

    private fun initData() {
        educationList = listOf("博士", "硕士", "本科", "专科", "高中/职高/技校", "其他")
        enterDateList = MutableList(31) { "${2021 - it}" }
    }

    private fun setListener() {
        educationSelectCallback = { i ->
            mBinding!!.activityMineInfoEducationEducationSelectTextView.text = educationList[i]
        }
        enterDateSelectCallback = { i ->
            mBinding!!.activityMineInfoEducationEnterDateSelectTextView.text = enterDateList[i]
        }
    }

    private fun initListener() {
        mBinding!!.activityMineInfoEducationBackLayout.setOnClickListener {
            finish()
        }
        mBinding!!.activityMineInfoEducationSaveLayout.setOnClickListener {
            if (check()) {
                finish()
            }
        }
        mBinding!!.activityMineInfoEducationEducationSelectLayout.setOnClickListener {
            MineInfoSingleDataDialogUtil().init(this, educationList, educationSelectCallback)
        }
        mBinding!!.activityMineInfoEducationEnterDateSelectLayout.setOnClickListener {
            MineInfoSingleDataDialogUtil().init(this, enterDateList, enterDateSelectCallback)
        }

    }
}