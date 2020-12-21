package com.example.mydesign.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.mydesign.R

class ChooseHeadImageDialogUtil {
    private lateinit var chooseHeadImageDialog: Dialog
    private lateinit var chooseHeadInflater: View
    private lateinit var chooseHeadLineView: View
    private lateinit var chooseHeadFromAlbum: TextView
    private lateinit var chooseHeadFromCamera: TextView
    private lateinit var chooseHeadCancel: TextView

    fun initChooseHeadImageDialog(
        context: Context,
        albumCallback: () -> Unit,
        cameraCallback: () -> Unit
    ) {
        chooseHeadImageDialog = Dialog(context, R.style.ChooseHeadImageDialog)
        chooseHeadInflater =
            LayoutInflater.from(context).inflate(R.layout.dialog_choose_head_image, null)
        chooseHeadFromAlbum = chooseHeadInflater.findViewById(R.id.dialog_choose_head_from_album)
        chooseHeadLineView = chooseHeadInflater.findViewById(R.id.dialog_choose_head_line_view)
        chooseHeadCancel = chooseHeadInflater.findViewById(R.id.dialog_choose_head_cancel)
        chooseHeadFromCamera = chooseHeadInflater.findViewById(R.id.dialog_choose_head_from_camera)
        chooseHeadLineView.background = ColorDrawable(Color.parseColor("#1A8F8F8F"))
        chooseHeadImageDialog.setContentView(chooseHeadInflater)
        val dialogWindow = chooseHeadImageDialog.window
        val windowManager = dialogWindow?.windowManager
        val display = windowManager?.defaultDisplay
        val layoutParams = dialogWindow?.attributes
        val point = Point()
        display?.getSize(point)
        layoutParams?.width = point.x
        dialogWindow?.setGravity(Gravity.BOTTOM)
        dialogWindow?.attributes = layoutParams
        chooseHeadImageDialog.show()
        initChooseHeadFromAlbum(albumCallback)
        initChooseHeadFromCamera(cameraCallback)
        initChooseHeadCancel()
    }

    private fun initChooseHeadFromCamera(callback: () -> Unit) {
        chooseHeadFromCamera.setOnClickListener {
            callback()
            chooseHeadImageDialog.dismiss()
        }
    }

    private fun initChooseHeadFromAlbum(callback: () -> Unit) {
        chooseHeadFromAlbum.setOnClickListener {
            callback()
            chooseHeadImageDialog.dismiss()
        }
    }

    private fun initChooseHeadCancel() {
        chooseHeadCancel.setOnClickListener {
            chooseHeadImageDialog.dismiss()
        }

    }
}