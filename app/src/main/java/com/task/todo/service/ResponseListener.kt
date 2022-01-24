package com.task.todo.service

import org.json.JSONException

interface ResponseListener {
    @Throws(JSONException::class)
    fun onLoad(responseString: String?)
    fun onFail(errorString: String?)

}