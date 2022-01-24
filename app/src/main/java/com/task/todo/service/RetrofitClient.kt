package com.task.todo.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        fun callAPI(): ApiManager? {
            var _retrofit = Retrofit.Builder()
                .baseUrl("https://task.fltstaging.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return _retrofit.create(ApiManager::class.java)
        }
    }

}