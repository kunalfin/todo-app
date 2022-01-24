package com.task.todo.service

import android.content.Context
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DataService {


    private var context
            : Context? = null

    constructor(cn: Context?) {
        this.context = cn
    }


    fun reqCommonApi(
        endpoint: String,
        jsonObject: JsonObject,
        listener: ResponseListener
    ) {

        var call: Call<ResponseBody?> = RetrofitClient.callAPI()?.getData(
            endpoint,
            jsonObject
        )!!

        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                var responseString: String? = null
                try {
                    if (response.isSuccessful && response.body() != null) {
                        responseString = response.body()!!.string()

                        listener.onLoad(responseString)
                    } else {
                        listener.onFail("Empty Data!")
                    }
                } catch (e: Exception) {
                    try {
                        listener.onFail(e.localizedMessage)
                    } catch (jsonException: JSONException) {
                        jsonException.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                try {
                    listener.onFail(t.localizedMessage)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }

}


