package com.example.plantshandbook
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val message: MutableLiveData<Character> by lazy {
        MutableLiveData<Character>()
    }
}