package com.example.mydesign.curriculumvitae

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R

/**
 * 个人简历
 */
class MineInfoCurriculumVitaeActivity : AppCompatActivity() {
    private lateinit var backLayout: FrameLayout
    private lateinit var emailLayout: FrameLayout
    private lateinit var emailTextView: TextView
    private lateinit var skillLayout: FrameLayout
    private lateinit var skillTextView: TextView
    private lateinit var schoolExperienceLayout: FrameLayout
    private lateinit var schoolExperienceTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curriculum_vitae)
        init()
        initListener()
    }

    private fun init() {
        backLayout = f(R.id.activity_curriculum_vitae_back_layout)
        emailLayout = f(R.id.activity_curriculum_vitae_email_layout)
        emailTextView = f(R.id.activity_curriculum_vitae_text_view)
        skillLayout = f(R.id.activity_curriculum_vitae_skill_layout)
        skillTextView = f(R.id.activity_curriculum_vitae_skill_text_view)
        schoolExperienceLayout = f(R.id.activity_curriculum_vitae_school_experience_layout)
        schoolExperienceTextView = f(R.id.activity_curriculum_vitae_school_experience_text_view)
    }

    private fun initListener() {
        backLayout.setOnClickListener {
            finish()
        }
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }
}