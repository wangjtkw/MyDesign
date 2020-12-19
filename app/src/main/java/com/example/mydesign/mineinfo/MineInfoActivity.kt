package com.example.mydesign.mineinfo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.mydesign.mineinfoeducation.MineInfoEducationActivity
import com.example.mydesign.mineinfoeducation.MineInfoEducationActivity.Companion.EDUCATION_EXPERIENCE
import com.example.mydesign.mineinfoeducation.MineInfoEducationActivity.Companion.EDUCATION_EXPERIENCE_REQUEST_CODE
import com.example.mydesign.dialog.ChooseHeadImageDialogUtil
import com.example.mydesign.R
import com.example.mydesign.StatusBarUtils
import com.example.mydesign.bean.EducationExperience
import com.example.mydesign.dialog.ChooseDateDialogUtil
import com.example.mydesign.dialog.MineInfoSingleDataDialogUtil

class MineInfoActivity : AppCompatActivity() {
    private lateinit var backLayout: FrameLayout
    private lateinit var titleTextView: TextView
    private lateinit var saveLayout: FrameLayout
    private lateinit var headSelectLayout: LinearLayout
    private lateinit var headImageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var sexSelectLayout: FrameLayout
    private lateinit var sexTextView: TextView
    private lateinit var birthDaySelectLayout: FrameLayout
    private lateinit var birthDayTextView: TextView
    private lateinit var roleSelectLayout: FrameLayout
    private lateinit var roleTextView: TextView
    private lateinit var phoneNumTextView: TextView
    private lateinit var weChatEditText: EditText
    private lateinit var qqEditText: EditText
    private lateinit var educationSelectLayout: LinearLayout
    private lateinit var educationSchoolTextView: TextView
    private lateinit var educationIntoYearTextView: TextView
    private lateinit var educationRecordTextView: TextView
    private lateinit var educationMajorTextView: TextView
    private lateinit var nextStepButton: Button

    private lateinit var sexDataList: List<String>
    private lateinit var roleDataList: List<String>

    private lateinit var sexSelectCallback: (Int) -> Unit
    private lateinit var birthdaySelectCallBack: (String) -> Unit
    private lateinit var roleSelectCallback: (Int) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_mine_info)
        init()

    }

    private fun init() {
        backLayout = f(R.id.activity_mine_back_layout)//
        titleTextView = f(R.id.activity_mine_title_text_view)
        saveLayout = f(R.id.activity_mine_save_layout)
        headSelectLayout = f(R.id.activity_mine_head_select_layout)//设置页面跳转
        headImageView = f(R.id.activity_mine_head_select_image_view)
        nameEditText = f(R.id.activity_mine_name_edit_text)
        sexSelectLayout = f(R.id.activity_mine_sex_select_layout)//
        sexTextView = f(R.id.activity_mine_sex_select_text_view)//
        birthDaySelectLayout = f(R.id.activity_mine_birthday_select_layout)//
        birthDayTextView = f(R.id.activity_mine_birthday_select_text_view)//
        roleSelectLayout = f(R.id.activity_mine_role_select_layout)//
        roleTextView = f(R.id.activity_mine_role_select_text_view)//
        phoneNumTextView = f(R.id.activity_mine_phone_num_text_view)
        weChatEditText = f(R.id.activity_mine_wechat_edit_text)
        qqEditText = f(R.id.activity_mine_qq_edit_text)
        educationSelectLayout = f(R.id.activity_mine_education_select_layout)
        educationSchoolTextView = f(R.id.activity_mine_education_school_text_view)
        educationIntoYearTextView = f(R.id.activity_mine_education_into_year_text_view)
        educationRecordTextView = f(R.id.activity_mine_education_record_text_view)
        educationMajorTextView = f(R.id.activity_mine_education_major_text_view)
        nextStepButton = f(R.id.activity_mine_next_step_button)
        initData()
        initListener()
        setListener()
    }

    private fun initData() {
        sexDataList = listOf("男", "女")
        roleDataList = listOf("学生党", "上班族", "自由职业")
    }

    private fun initListener() {
        sexSelectCallback = { i ->
            sexTextView.text = sexDataList[i]
            sexTextView.setTextColor(Color.parseColor("#8A000000"))
        }
        birthdaySelectCallBack = { s ->
            birthDayTextView.text = s
            birthDayTextView.setTextColor(Color.parseColor("#8A000000"))
        }
        roleSelectCallback = { i ->
            roleTextView.text = roleDataList[i]
            roleTextView.setTextColor(Color.parseColor("#8A000000"))
        }
        educationSelectLayout.setOnClickListener {
            val intent = Intent(this, MineInfoEducationActivity::class.java)
            intent.putExtra(
                EDUCATION_EXPERIENCE,
                MineInfoDataProvider().provide().educationExperience
            )
            startActivityForResult(intent, EDUCATION_EXPERIENCE_REQUEST_CODE)
        }

    }

    private fun setListener() {
        backLayout.setOnClickListener { this.finish() }
        headSelectLayout.setOnClickListener {
            ChooseHeadImageDialogUtil().initChooseHeadImageDialog(
                this,
                {})
        }
        sexSelectLayout.setOnClickListener {
            MineInfoSingleDataDialogUtil().init(this, sexDataList, sexSelectCallback)
        }
        birthDaySelectLayout.setOnClickListener {
            ChooseDateDialogUtil().init(this, birthdaySelectCallBack)
        }
        roleSelectLayout.setOnClickListener {
            MineInfoSingleDataDialogUtil().init(this, roleDataList, roleSelectCallback)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDUCATION_EXPERIENCE_REQUEST_CODE && resultCode == EDUCATION_EXPERIENCE_REQUEST_CODE && data != null) {
            getMineInfoEducationActivityResult(data)
        }
    }

    private fun getMineInfoEducationActivityResult(data: Intent) {
        val educationExperience = data.getParcelableExtra<EducationExperience>(EDUCATION_EXPERIENCE)
        educationExperience.let {
            educationSchoolTextView.text = it?.school
            educationIntoYearTextView.text = it?.enterDate
            educationRecordTextView.text = it?.education
            educationMajorTextView.text = it?.major
        }
    }



    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }


}