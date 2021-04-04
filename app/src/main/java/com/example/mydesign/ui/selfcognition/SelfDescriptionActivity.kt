package com.example.mydesign.ui.selfcognition

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R
import com.example.mydesign.utils.StatusBarUtils

/**
 * 自我描述
 */
class SelfDescriptionActivity : AppCompatActivity() {
    private lateinit var cancelLayout: FrameLayout
    private lateinit var selfDescriptionEditText: EditText
    private lateinit var selfDescriptionTextView: TextView
    private lateinit var saveButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_self_description)
        init()
        initListener()
    }

    private fun init() {
        cancelLayout = f(R.id.activity_self_description_cancel_layout)
        selfDescriptionEditText = f(R.id.activity_self_description_edit_text)
        selfDescriptionTextView = f(R.id.activity_self_description_text_view)
        saveButton = f(R.id.activity_self_description_save_button)
    }

    private fun initListener() {
        cancelLayout.setOnClickListener {
            finish()
        }
        saveButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra(
                MineInfoSelfCognitionActivity.SELF_DESCRIPTION,
                selfDescriptionEditText.text
            )
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }
}