package com.example.myapplication_labexam3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication_labexam3.database.Shop

class ShopActivityData: ViewModel() {
    private val _data = MutableLiveData<List<Shop>>()
    val data: LiveData<List<Shop>> = _data
    fun setData(data:List<Shop>){
        _data.value = data
    }
}