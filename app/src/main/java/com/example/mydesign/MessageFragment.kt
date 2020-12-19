package com.example.mydesign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.mydesign.base.BaseFragment
import com.example.mydesign.myview.PickerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MessageFragment : BaseFragment() {
    private lateinit var pickerView: DatePicker


    override fun getLayoutID() = R.layout.fragment_message

    override fun init() {
        pickerView = f(R.id.text_date_picker)
//        pickerView.
        Utils.setDatePickerDividerColor(pickerView)
    }


}