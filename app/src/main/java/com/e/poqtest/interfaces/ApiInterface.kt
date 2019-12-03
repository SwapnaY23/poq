package com.e.poqtest.interfaces

import com.e.poqtest.DataModel.Model
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("repos")
    fun getData(): Call<List<Model>>

}