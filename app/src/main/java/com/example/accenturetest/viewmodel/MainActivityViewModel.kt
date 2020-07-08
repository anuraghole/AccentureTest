package com.example.accenturetest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.accenturetest.data.ResponseData
import com.example.accenturetest.data.Row
import com.example.accenturetest.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    val TAG = MainActivityViewModel::class.java.simpleName
    var responseData = MutableLiveData<ResponseData>()

    fun getResponseApiCall() {
        val call = RetrofitBuilder.apiService.getResponseData()
        call.enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful)
                    responseData.postValue(response.body())
                else
                    Log.d(TAG, "onResponse: "+response)
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t)
            }
        })
    }

}