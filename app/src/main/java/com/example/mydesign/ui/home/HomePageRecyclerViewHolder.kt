package com.example.mydesign.ui.home

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.mydesign.R
import com.example.mydesign.common.DataBindingViewHolder
import com.example.mydesign.data.bean.entity.JobPositionEntity
import com.example.mydesign.databinding.ItemHomePageRecyclerBinding
import com.example.mydesign.publicclass.ViewHolder
import com.example.mydesign.ui.positiondetail.PositionDetailActivity


class HomePageRecyclerViewHolder(view: View) : DataBindingViewHolder<JobPositionEntity>(view) {
    private val mBinding: ItemHomePageRecyclerBinding = DataBindingUtil.bind(view)!!

    fun buildTagView(text: String): FrameLayout {
        val textView = TextView(view.context)
        textView.apply {
            setTextColor(Color.parseColor("#BFFF0057"))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            setPadding(10, 4, 10, 4)
            setText(text)
        }
        val frameLayout = FrameLayout(view.context)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            frameLayout.apply {
                background = view.context.getDrawable(R.drawable.shape_circle_corner_red)
                addView(textView)
                val lp = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                lp.setMargins(0, 0, 20, 0)
                setLayoutParams(lp)
            }
        }

        return frameLayout
    }

    override fun bindData(data: JobPositionEntity, position: Int) {
        mBinding.jobPositionEntity = data
        val list = getWelfare(data.employerPositionWelfare ?: "")
        mBinding.itemHomePageTagLayout.apply {
            list.map {
                addView(buildTagView(it))
            }
        }
        mBinding.itemHomePageLayout.setOnClickListener {
            PositionDetailActivity.actionStart(context(), data.employerPositionId!!)
        }
    }

    private fun getWelfare(str: String): ArrayList<String> {
        val strList = ArrayList<String>()
        if (str.isEmpty()) {
            return strList
        }
        var p = 0
        val indexList = ArrayList<Int>()
        while (p < str.length) {
            if (str[p] == '￥') {
                indexList.add(p)
            }
            p++
        }
        p = 0
        strList.add(str.substring(0, indexList[p]))
        while (p < indexList.size - 1) {
            strList.add(str.substring(indexList[p] + 1, indexList[++p]))
        }
        strList.add(str.substring(indexList[p] + 1, str.length))
        return strList
    }
}