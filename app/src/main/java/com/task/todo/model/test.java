package com.task.todo.model;

import com.google.gson.annotations.SerializedName;

public class test {

    /**
     * status : 200
     * message : Task removed successfully
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
