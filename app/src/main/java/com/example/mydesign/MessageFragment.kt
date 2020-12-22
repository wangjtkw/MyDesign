package com.example.mydesign

import android.widget.DatePicker
import com.example.mydesign.album.AlbumSelectUtils
import com.example.mydesign.base.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MessageFragment : BaseFragment() {
    private lateinit var pickerView: DatePicker


    override fun getLayoutID() = R.layout.fragment_message

    override fun init() {
//        pickerView = f(R.id.text_date_picker)
////        pickerView.
//        Utils.setDatePickerDividerColor(pickerView)
        AlbumSelectUtils().getImages(fragmentView.context)
    }


}