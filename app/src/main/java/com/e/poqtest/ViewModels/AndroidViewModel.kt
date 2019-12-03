package com.e.poqtest.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.e.poqtest.CommonService.RetrofitService
import com.e.poqtest.DataModel.Model

class AndroidViewModel: ViewModel() {

    private val mService  =  RetrofitService()

    fun getData() : MutableLiveData<List<Model>>? {
        return mService.loadData()
    }
    var isLoading = mService.progress

}
