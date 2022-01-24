package com.task.todo.ui.activity

import android.app.Activity
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.task.todo.databinding.ActivityEditTaskBinding
import com.task.todo.model.AddTaskModel
import com.task.todo.model.TaskModel
import com.task.todo.service.DataService
import com.task.todo.service.ResponseListener
import com.task.todo.utils.MyPreferences
import com.task.todo.utils.OnClickDialog
import com.task.todo.utils.ProgressBar
import com.task.todo.utils.Utils
import java.util.*

class EditTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTaskBinding
    private lateinit var activity: Activity
    private lateinit var myPreferences: MyPreferences
    private lateinit var progressBar: ProgressBar
    private var taskName: String? = null
    private var taskDate: String? = null
    private var taskDescription: String? = null
    private var task_id: String? = null
    private var dateString: String? = null
    private var isTaskName: Boolean = false
    private var isTaskDate: Boolean = false
    private var isTaskDescription: Boolean = false
    private var model: TaskModel.DataBean.TasksBean? = null

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
        myPreferences = MyPreferences(activity)
        progressBar = ProgressBar(activity, false)
        task_id = intent.getStringExtra("task_id").toString()
        Utils.setHeader(activity, "Edit Task", binding.toolbar)

        val bundle = intent.getBundleExtra("BUNDLE")!!
        model = bundle.getSerializable(Utils.TASK_DATA) as TaskModel.DataBean.TasksBean?
        var taskName:String = model!!.task.toString()
        var taskDate:String = model!!.date.toString()
        var taskDesc:String = model!!.description.toString()
        task_id = model!!.id.toString()

//            binding.txtDescription.setText(Html.fromHtml(desc));
        binding.taskNameEditText.setText(model!!.task.toString())
        binding.dateEditText.setText(model!!.date.toString())
        binding.descNameEditText.setText(model!!.description.toString())

        /*binding.taskNameEditText.text = taskName
        binding.dateEditText.text = model!!.task.toString()
        binding.descNameEditText.text = model.description*/

        binding.saveBtn.setOnClickListener(View.OnClickListener {
            Utils.showDialog(activity,"Are you sure you want to edit the task? ",object:
                OnClickDialog {
                override fun onClick(isYes: Boolean) {
                    if (isYes){
                        validDetails()
                    }
                }
            } )

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
        if (binding.descNameEditText.text.toString().isEmpty()) {
            binding.descNameEditText.error = "Task Description is empty"
            isTaskDescription = false
        } else {
            isTaskDescription = true
        }

        if (isTaskName && isTaskDate && isTaskDescription) {
            taskName = binding.taskNameEditText.text.toString()
            taskDate = binding.dateEditText.text.toString()
            taskDescription = binding.descNameEditText.text.toString()
            val jsonObject = JsonObject()
            jsonObject.addProperty(Utils.task, taskName)
            jsonObject.addProperty(Utils.date, dateString)
            jsonObject.addProperty(Utils.description, taskDescription)
            editTask(jsonObject)

        }
    }

    private fun editTask(jsonObject: JsonObject) {

        jsonObject.addProperty(Utils.task_id, task_id)
        progressBar.show()
        DataService(applicationContext).reqCommonApi(
            Utils.updateTask,
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