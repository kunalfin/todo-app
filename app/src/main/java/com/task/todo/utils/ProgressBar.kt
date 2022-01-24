package com.task.todo.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.task.todo.R

class ProgressBar {

    private var alertDialog: AlertDialog? = null

    constructor(activity: Activity, cancelable: Boolean) {
        val dialogBuilder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.progressbar, null)
        dialogBuilder.setView(dialogView)
        alertDialog = dialogBuilder.create()
        alertDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog!!.setCancelable(cancelable)
    }



    fun dismiss() {
        if (alertDialog != null && alertDialog!!.isShowing) alertDialog!!.dismiss()
    }


    fun show() {
        if (alertDialog != null && !alertDialog!!.isShowing) alertDialog!!.show()
    }
}