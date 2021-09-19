package com.example.baloot.alimehdizadeh.presentation.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baloot.alimehdizadeh.utils.Response

open class BaseViewModel :ViewModel(){

    var scope = viewModelScope
    var errorLiveData = MutableLiveData<String>()
    fun <T> handleResponse(response: Response<T>): T? {
        return when (response) {
            is Response.ErrorState -> {
                Log.d("Response", "Error")
                null
            }
            is Response.LoadingState -> {
                Log.d("Response", "Starting")
                null
            }
            is Response.SuccessState -> {
                response.data
            }
        }
    }
}