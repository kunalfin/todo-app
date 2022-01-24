package com.task.todo.model

import com.google.gson.annotations.SerializedName

class AddTaskModel {

    /**
     * status : 200
     * message : Task added successfully
     * data : {"id":22,"task":"Test","date":"2022-01-21","description":"Starting with desktop apps that target the .NET Framework 4.6, the culture of the thread that creates and invokes a task becomes part of the thread's context."}
     */
    @SerializedName("status")
    private var status = 0

    @SerializedName("message")
    private var message: String? = null

    /**
     * id : 22
     * task : Test
     * date : 2022-01-21
     * description : Starting with desktop apps that target the .NET Framework 4.6, the culture of the thread that creates and invokes a task becomes part of the thread's context.
     */
    @SerializedName("data")
    private var data: DataBean? = null

    fun getStatus(): Int {
        return status
    }

    fun setStatus(status: Int) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getData(): DataBean? {
        return data
    }

    fun setData(data: DataBean?) {
        this.data = data
    }

    class DataBean {
        @SerializedName("id")
        var id = 0

        @SerializedName("task")
        var task: String? = null

        @SerializedName("date")
        var date: String? = null

        @SerializedName("description")
        var description: String? = null
    }

}