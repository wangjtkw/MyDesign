package com.example.mydesign.curriculumvitae

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R
import com.example.mydesign.constants.SCHOOL_SHOW_TO_SCHOOL_FILL_REQUEST_CODE
import com.example.mydesign.utils.StatusBarUtils

class SchoolExperienceShowActivity : AppCompatActivity() {
    private lateinit var backLayout: FrameLayout
    private lateinit var addTargetLayout: LinearLayout
    private lateinit var addItemLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_school_experience_show)
        init()
        initListener()
    }

    private fun init() {
        backLayout = f(R.id.activity_curriculum_vitae_school_experience_show_back_layout)
        addTargetLayout = f(R.id.activity_curriculum_vitae_school_experience_show_add_target_layout)
        addItemLayout = f(R.id.activity_curriculum_vitae_school_experience_show_add_item_layout)
    }

    private fun initListener() {
        backLayout.setOnClickListener {
            finish()
        }
        addItemLayout.setOnClickListener {
            val intent = Intent(this, SchoolExperienceFillActivity::class.java)
            startActivityForResult(intent, SCHOOL_SHOW_TO_SCHOOL_FILL_REQUEST_CODE)
        }
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }
}