package com.example.mydesign.curriculumvitae

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R
import com.example.mydesign.constants.VITAE_EMILE_REQUEST_CODE
import com.example.mydesign.constants.VITAE_INTERNSHIP_SHOW_REQUEST_CODE
import com.example.mydesign.constants.VITAE_SCHOOL_SHOW_REQUEST_CODE
import com.example.mydesign.constants.VITAE_SKILL_REQUEST_CODE
import com.example.mydesign.utils.StatusBarUtils

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
    private lateinit var internshipExperienceLayout: FrameLayout
    private lateinit var internshipExperienceTextView: TextView
    private lateinit var finishButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
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
        internshipExperienceLayout = f(R.id.activity_curriculum_vitae_internship_experience_layout)
        internshipExperienceTextView =
            f(R.id.activity_curriculum_vitae_internship_experience_text_view)
        finishButton = f(R.id.activity_curriculum_vitae_internship_experience_finish_button)

    }

    private fun initListener() {
        backLayout.setOnClickListener {
            finish()
        }
        emailLayout.setOnClickListener {
            val intent = Intent(this, EmileActivity::class.java)
            startActivityForResult(intent, VITAE_EMILE_REQUEST_CODE)
        }
        skillLayout.setOnClickListener {
            val intent = Intent(this, SkillAndCertificateActivity::class.java)
            startActivityForResult(intent, VITAE_SKILL_REQUEST_CODE)
        }
        schoolExperienceLayout.setOnClickListener {
            val intent = Intent(this, SchoolExperienceShowActivity::class.java)
            startActivityForResult(intent, VITAE_SCHOOL_SHOW_REQUEST_CODE)
        }
        internshipExperienceLayout.setOnClickListener {
            val intent = Intent(this, InternshipExperienceShowActivity::class.java)
            startActivityForResult(intent, VITAE_INTERNSHIP_SHOW_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VITAE_EMILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

        }
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }
}