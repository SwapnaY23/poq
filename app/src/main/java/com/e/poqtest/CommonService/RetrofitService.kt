package com.e.poqtest.CommonService

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.e.poqtest.DataModel.Model
import com.e.poqtest.interfaces.ApiInterface
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val liveUserResponse: MutableLiveData<List<Model>> = MutableLiveData()
    var progress = MutableLiveData<Boolean>()
    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {
            Log.e("retrofit","create")
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://api.github.com/orgs/square/")
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

    fun loadData(): MutableLiveData<List<Model>>? {

        Log.e("loadData","yes")
        progress.value=true
        val retrofitCall  = create().getData()
        retrofitCall.enqueue(object : Callback<List<Model>> {
            override fun onFailure(call: Call<List<Model>>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
                progress.value=false
            }
            override fun onResponse(call: Call<List<Model>>, response: retrofit2.Response<List<Model>>) {
                progress.value=false
                val list  = response.body()
                for (i in list.orEmpty()){
                    Log.e("on response 1:", i.full_name)
                }

                liveUserResponse.value = list

                Log.e("hasActiveObservers 1", liveUserResponse.hasActiveObservers().toString()+" check")

                Log.e("on response 2 :", liveUserResponse.toString()+" check")

            }

        })

        return liveUserResponse
    }
}
