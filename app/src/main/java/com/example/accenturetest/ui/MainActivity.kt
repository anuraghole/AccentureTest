package com.example.accenturetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.accenturetest.R
import com.example.accenturetest.data.ResponseData
import com.example.accenturetest.data.Row
import com.example.accenturetest.databinding.ActivityMainBinding
import com.example.accenturetest.ui.adapter.RowAdapter
import com.example.accenturetest.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    val TAG=MainActivity::class.java.simpleName
    var viewModel = MainActivityViewModel()
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        callApi()
        setListener()
        setObserver()
    }

    private fun setListener() {
        //refresh new list on swipe
        binding.swipeRefresh.setOnRefreshListener {
            callApi()
            binding.swipeRefresh.isRefreshing=false
        }
    }

    private fun setObserver() {
        viewModel.responseData.observe(this,object :Observer<ResponseData>{
            override fun onChanged(responseData: ResponseData?) {
                Log.i(TAG, "onChanged: "+responseData)
                title=responseData?.title
                var list= mutableListOf<Row>()

                //if title,description, and image url is null or blank dont add the item in list
                for (item in responseData?.rows!!){
                    if (item.description.isNullOrBlank() && item.imageHref.isNullOrBlank() && item.title.isNullOrBlank()) {
                    }else{
                        list.add(item)
                    }
                }
                Log.i(TAG, "list: "+list)
                binding.rvRow.adapter= responseData?.rows?.let { RowAdapter(this@MainActivity, list) }
            }
        })
    }

    private fun init() {
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel=ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        setSupportActionBar(binding.toolbar)
    }
    private fun callApi() {
        viewModel.getResponseApiCall()
    }
}