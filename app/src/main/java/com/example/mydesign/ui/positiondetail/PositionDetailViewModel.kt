package com.example.mydesign.ui.positiondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydesign.data.datasource.PositionDetailDataSource
import javax.inject.Inject

class PositionDetailViewModel @Inject constructor(
    private val positionDetailDataSource: PositionDetailDataSource
) : ViewModel() {

    fun getJobDetail(positionId: Int) =
        positionDetailDataSource.getDetail(viewModelScope, positionId)

}