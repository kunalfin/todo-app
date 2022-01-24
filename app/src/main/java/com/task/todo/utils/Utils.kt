package com.task.todo.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.task.todo.R
import com.task.todo.databinding.DialogBinding
import com.task.todo.databinding.ItemHeaderBinding
import com.task.todo.model.AddTaskModel
import com.task.todo.model.DeleteTaskModel
import com.task.todo.service.DataService
import com.task.todo.service.ResponseListener

import java.lang.Exception


object Utils {

    // Api EndPoints
    const val addTask = "add-task"
    const val taskList = "task-list"
    const val removeTask = "remove-task"
    const val updateTask = "update-task"


    const val ITEMS_PER_PAGE = 10


    // Pref Key
    const val page = "page"
    const val task = "task"
    const val date = "date"
    const val description = "description"
    const val task_id = "task_id"

    const val TASK_DATA = "TASK_DATA"


    fun isLoadMore(position: Int, listSize: Int): Boolean {
        if (position == listSize - 1) {
            return (position + 1) % Utils.ITEMS_PER_PAGE === 0
        }
        return position == listSize + 1 && listSize > ITEMS_PER_PAGE
    }

    fun showDialog(activity: Activity, msg: String, objects: OnClickDialog) {
        try {
            val builder = AlertDialog.Builder(activity)
            val view: View = activity.layoutInflater.inflate(R.layout.dialog, null)
            builder.setView(view)
            val dialogBinding: DialogBinding = DialogBinding.bind(view)
            val dialog = builder.create()
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogBinding.desc.text = msg
            dialogBinding.btnYes.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
                objects.onClick(true)
            })
            dialogBinding.btnCancel.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
                objects.onClick(false)
            })

            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setHeader(activity: Activity, title: String, toolbar: ItemHeaderBinding) {
        toolbar.btnBack.isVisible = !title.equals("home", true)
        toolbar.title.text = title
        toolbar.btnBack.setOnClickListener(View.OnClickListener { activity.onBackPressed() })
    }

    fun deleteTask(activity: Activity, jsonObject: JsonObject) {

        DataService(activity).reqCommonApi(
            removeTask,
            jsonObject,
            object : ResponseListener {

                override fun onLoad(responseString: String?) {
                    val pojo: DeleteTaskModel =
                        Gson().fromJson(responseString, DeleteTaskModel::class.java)

                    if (pojo.getStatus() == 200) {
                        Toast.makeText(activity, pojo.getMessage(), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, pojo.getMessage(), Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFail(errorString: String?) {
                    Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show()

                }
            })
    }

}