package com.task.todo.service

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiManager {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("{method}")
    fun getData(
        @Path("method") method: String?,
        @Body jsonObject: JsonObject?
    ): Call<ResponseBody?>?


}