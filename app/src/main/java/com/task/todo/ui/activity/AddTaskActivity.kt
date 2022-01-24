package com.task.todo.ui.activity

import android.app.Activity
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.task.todo.R
import com.task.todo.databinding.ActivityAddTaskBinding
import com.task.todo.databinding.ActivityMainBinding
import com.task.todo.model.AddTaskModel
import com.task.todo.model.TaskModel
import com.task.todo.service.DataService
import com.task.todo.service.ResponseListener
import com.task.todo.utils.MyPreferences
import com.task.todo.utils.ProgressBar
import com.task.todo.utils.Utils
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var activity: Activity
    private lateinit var myPreferences: MyPreferences
    private lateinit var progressBar: ProgressBar
    private var taskName: String? = null
    private var taskDate: String? = null
    private var dateString: String? = null
    private var taskDescription: String? = null
    private var isTaskName: Boolean = false
    private var isTaskDate: Boolean = false
    private var isTaskDescription: Boolean = false
    var cal = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
        myPreferences = MyPreferences(activity)
        progressBar = ProgressBar(activity, false)

        Utils.setHeader(activity, "Add Task", binding.toolbar)

        binding.saveBtn.setOnClickListener(View.OnClickListener {
            validDetails()
        })

        // create an OnDateSetListener
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear + 1)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                 val display_dateString = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                dateString = if ((monthOfYear + 1).toString().length ==1){
                    year.toString()+ "-0" + (monthOfYear + 1).toString() + "-" + dayOfMonth.toString()
                }else{
                    year.toString()+ "-" + (monthOfYear + 1).toString() + "-" + dayOfMonth.toString()
                }

                binding.dateEditText.setText(display_dateString)
            }

        binding.btnCal.setOnClickListener {
            DatePickerDialog(
                activity,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


    }

    private fun validDetails() {


        // Check for a valid Task name.
        if (binding.taskNameEditText.text.toString().isEmpty()) {
            binding.taskNameEditText.error = "Task name is empty"
            isTaskName = false
        } else {
            isTaskName = true
        }

        // Check for a valid Date.
        if (binding.dateEditText.text.toString().isEmpty()) {
            isTaskDate = false
            binding.dateEditText.error = "Please select Date First"
        } else {
            isTaskDate = true
        }


        // Check for a valid Task name.
        if (binding.taskDescEditText.text.toString().isEmpty()) {
            binding.taskDescEditText.error = "Task Description is empty"
            isTaskDescription = false
        } else {
            isTaskDescription = true
        }

        if (isTaskName && isTaskDate && isTaskDescription) {
            taskName = binding.taskNameEditText.text.toString()
            taskDate = binding.dateEditText.text.toString()
            taskDescription = binding.taskDescEditText.text.toString()
            val jsonObject = JsonObject()
            jsonObject.addProperty(Utils.task, taskName)
            jsonObject.addProperty(Utils.date, dateString)
            jsonObject.addProperty(Utils.description, taskDescription)
            addTask(jsonObject)

        }
    }

    private fun addTask(jsonObject: JsonObject) {

        progressBar.show()
        DataService(applicationContext).reqCommonApi(
            Utils.addTask,
            jsonObject,
            object : ResponseListener {

                override fun onLoad(responseString: String?) {
                    progressBar.dismiss()
                    val pojo: AddTaskModel =
                        Gson().fromJson(responseString, AddTaskModel::class.java)

                    if (pojo.getStatus() == 200) {
                        Toast.makeText(activity, pojo.getMessage(), Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(activity, pojo.getMessage(), Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFail(errorString: String?) {
                    progressBar.dismiss()
                    Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show()

                }
            })
    }
}