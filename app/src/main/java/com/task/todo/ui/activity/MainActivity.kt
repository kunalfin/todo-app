package com.task.todo.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.task.todo.databinding.ActivityMainBinding
import com.task.todo.model.TaskModel
import com.task.todo.service.DataService
import com.task.todo.service.ResponseListener
import com.task.todo.ui.adapter.TaskAdapter
import com.task.todo.utils.MyPreferences
import com.task.todo.utils.PaginationListener
import com.task.todo.utils.ProgressBar
import com.task.todo.utils.Utils
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var activity: Activity
    private lateinit var myPreferences: MyPreferences
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: TaskAdapter
    private var page = 0
    private var todoList: ArrayList<TaskModel.DataBean.TasksBean> =
        ArrayList<TaskModel.DataBean.TasksBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
        myPreferences = MyPreferences(activity)
        progressBar = ProgressBar(activity, false)

        Utils.setHeader(activity, "Home", binding.toolbar)


        setAdapter()
        getToDOList()
        setupFloatingActionButton()

    }

    private fun setAdapter() {
        binding.recTodo.layoutManager = LinearLayoutManager(activity)
        adapter = TaskAdapter(activity, todoList, object : PaginationListener {
            override fun onPage() {
                page = 0
                page = todoList.size / Utils.ITEMS_PER_PAGE
                getToDOList()
            }
        })
        binding.recTodo.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        getToDOList()
    }
    private fun getToDOList() {

        val jsonObject = JsonObject()
        jsonObject.addProperty(Utils.page, page)
        progressBar.show()
        DataService(applicationContext).reqCommonApi(
            Utils.taskList,
            jsonObject,
            object : ResponseListener {

                override fun onLoad(responseString: String?) {
                    progressBar.dismiss()
                    val pojo: TaskModel = Gson().fromJson(responseString, TaskModel::class.java)
                    if (pojo.getStatus() == 200) {
                        pojo.getData()!!.tasks?.let { todoList.addAll(it) }
                        adapter.notifyDataSetChanged()
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

    private fun setupFloatingActionButton() {
        binding.addTask.setOnClickListener {
            startActivity(Intent(activity, AddTaskActivity::class.java))
        }
    }
}