package com.example.mydesign.mineinfoeducation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R
import com.example.mydesign.bean.EducationExperience
import com.example.mydesign.dialog.MineInfoSingleDataDialogUtil
import com.example.mydesign.mineinfo.MineInfoActivity.Companion.EDUCATION_EXPERIENCE
import com.example.mydesign.mineinfo.MineInfoActivity.Companion.EDUCATION_EXPERIENCE_REQUEST_CODE
import com.example.mydesign.utils.StatusBarUtils

class MineInfoEducationActivity : AppCompatActivity() {
    private lateinit var backLayout: FrameLayout
    private lateinit var saveLayout: FrameLayout
    private lateinit var educationSelectLayout: FrameLayout
    private lateinit var educationSelectTextView: TextView
    private lateinit var schoolSelectLayout: FrameLayout
    private lateinit var schoolSelectTextView: TextView
    private lateinit var enterDateSelectLayout: FrameLayout
    private lateinit var enterDateSelectTextView: TextView
    private lateinit var majorEditText: EditText
    private var educationExperienceBean: EducationExperience? = null

    private lateinit var educationList: List<String>
    private lateinit var enterDateList: List<String>

    private lateinit var educationSelectCallback: (Int) -> Unit
    private lateinit var enterDateSelectCallback: (Int) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_mine_info_education)
        educationExperienceBean = intent.getParcelableExtra(EDUCATION_EXPERIENCE)
        init()
    }

    private fun init() {
        backLayout = f(R.id.activity_mine_info_education_back_layout)
        saveLayout = f(R.id.activity_mine_info_education_save_layout)
        educationSelectLayout = f(R.id.activity_mine_info_education_education_select_layout)
        educationSelectTextView = f(R.id.activity_mine_info_education_education_select_text_view)
        schoolSelectLayout = f(R.id.activity_mine_info_education_school_select_layout)
        schoolSelectTextView = f(R.id.activity_mine_info_education_school_select_text_view)
        enterDateSelectLayout = f(R.id.activity_mine_info_education_enter_date_select_layout)
        enterDateSelectTextView = f(R.id.activity_mine_info_education_enter_date_select_text_view)
        majorEditText = f(R.id.activity_mine_info_education_major_edit_text)
        initData()
        setListener()
        initListener()
    }

    private fun initData() {
        educationList = listOf("博士", "硕士", "本科", "专科", "高中/职高/技校", "其他")
        enterDateList = MutableList(31) { "${2021 - it}" }
    }

    private fun setListener() {
        educationSelectCallback = { i ->
            educationSelectTextView.text = educationList[i]
        }
        enterDateSelectCallback = { i ->
            enterDateSelectTextView.text = enterDateList[i]
        }
    }

    private fun initListener() {
        backLayout.setOnClickListener {
            finish()
        }
        saveLayout.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EDUCATION_EXPERIENCE, educationExperienceBean)
            setResult(EDUCATION_EXPERIENCE_REQUEST_CODE, intent)
            finish()
        }
        educationSelectLayout.setOnClickListener {
            MineInfoSingleDataDialogUtil().init(this, educationList, educationSelectCallback)
        }
        enterDateSelectLayout.setOnClickListener {
            MineInfoSingleDataDialogUtil().init(this, enterDateList, enterDateSelectCallback)
        }

    }


    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }
}