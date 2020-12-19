package com.example.mydesign.home

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mydesign.R
import com.example.mydesign.publicclass.ViewHolder


class HomePageRecyclerViewHolder(val view: View) : ViewHolder(view) {

    fun getLayout(id: Int): LinearLayout {
        return f(id)
    }

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
}