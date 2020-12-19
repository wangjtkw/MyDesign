package com.example.mydesign.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydesign.R
import com.example.mydesign.bean.HomePageItemBean


class RecyclerAdapter : RecyclerView.Adapter<HomePageRecyclerViewHolder>() {
    private var mCallback: (() -> Unit)? = null
    private val mData = ArrayList<HomePageItemBean>()
    private var count: Int = 0

    fun addData(data: List<HomePageItemBean>) {
        count = mData.size
        mData.addAll(data)
        notifyItemRangeChanged(count, data.size)
        Log.e("recycler", mData.size.toString())
    }

    fun setCallBack(callback: (() -> Unit)) {
        mCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageRecyclerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_page_recycler, parent, false)
//        if (mCallback == null){
//            throw Exception("${this.javaClass.simpleName} callback is")
//        }
        return HomePageRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomePageRecyclerViewHolder, position: Int) {
        mData[position].let {
            Log.e("recycler", it.company.toString())
            holder.setText(it.title, R.id.item_home_page_title)
                .setText(it.company, R.id.item_home_page_company)
                .setText(it.salary, R.id.item_home_page_salary)
            holder.setViewVisible(it.img != null && it.img != "", R.id.item_home_page_image_view)
            if (it.img != null && it.img != "") {
                holder.setImage(it.img, R.id.item_home_page_image_view)
            }

            holder.getLayout(R.id.item_home_page_tag_layout).apply {
                for (i in it.tags) {
                    addView(holder.buildTagView(i))
                }
            }
        }
    }


    override fun getItemCount() = mData.size
}

