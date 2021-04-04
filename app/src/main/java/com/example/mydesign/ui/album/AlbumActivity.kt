package com.example.mydesign.ui.album

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydesign.R
import com.example.mydesign.data.bean.AlbumBean
import com.example.mydesign.ui.mineinfo.MineInfoActivity
import com.example.mydesign.ui.mineinfo.MineInfoActivity.Companion.ALBUM_BEAN

class AlbumActivity : AppCompatActivity() {
    private lateinit var backLayout: FrameLayout
    private lateinit var recyclerView: RecyclerView

    private lateinit var recyclerItemCallback: (AlbumBean) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        init()
        initListener()
        setListener()
        initRecyclerView()
    }

    private fun init() {
        backLayout = f(R.id.activity_album_back_layout)
        recyclerView = f(R.id.activity_album_recycler_view)
    }

    private fun initListener() {
        backLayout.setOnClickListener {
            finish()
        }
    }

    private fun setListener() {
        recyclerItemCallback = {
            val intent = Intent()
            intent.putExtra(ALBUM_BEAN, it)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun initRecyclerView() {
        val adapter = AlbumRecyclerAdapter()
        adapter.setData(AlbumSelectUtils().getImages(this) ?: ArrayList())
        adapter.setCallback(recyclerItemCallback)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }

}