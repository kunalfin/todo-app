package com.task.todo.model

import com.google.gson.annotations.SerializedName

class DeleteTaskModel {

    /**
     * status : 200
     * message : Task removed successfully
     */
    @SerializedName("status")
    private var status = 0

    @SerializedName("message")
    private var message: String? = null

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

}