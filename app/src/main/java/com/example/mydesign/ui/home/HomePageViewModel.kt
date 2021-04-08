package com.example.mydesign.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydesign.data.datasource.HomePositionDataSource
import javax.inject.Inject

class HomePageViewModel @Inject constructor(
    private val dataSource: HomePositionDataSource
) : ViewModel() {

    fun getJobList(type: String?) = dataSource.getJobList(viewModelScope, type)

}