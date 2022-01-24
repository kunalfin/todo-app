package com.task.todo.ui.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.task.todo.R
import com.task.todo.databinding.ItemTaskBinding
import com.task.todo.model.TaskModel
import com.task.todo.ui.activity.EditTaskActivity
import com.task.todo.utils.OnClickDialog
import com.task.todo.utils.PaginationListener
import com.task.todo.utils.Utils
import java.util.*

class TaskAdapter(
    activity: Activity,
    todoList: ArrayList<TaskModel.DataBean.TasksBean>,
    pagination: PaginationListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var activity: Activity = activity
    var todoList: ArrayList<TaskModel.DataBean.TasksBean> = todoList
    var pagination: PaginationListener = pagination


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.item_task, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder) {
            val viewHolder: MyViewHolder = holder as MyViewHolder
            val binding: ItemTaskBinding = viewHolder.binding
            val model: TaskModel.DataBean.TasksBean = todoList[position]

            binding.txtTitle.text = model.task
            binding.txtDate.text = model.date
            binding.txtDesc.text = model.description

            binding.btnEdit.setOnClickListener(View.OnClickListener {
                val intent = Intent(activity, EditTaskActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable(Utils.TASK_DATA, model)
                intent.putExtra("BUNDLE", bundle)
                activity.startActivity(intent)

            })
            binding.btnDelete.setOnClickListener(View.OnClickListener {
                Utils.showDialog(activity,"Are you sure you want to delete the task?",object:OnClickDialog{
                    override fun onClick(isYes: Boolean) {
                        if (isYes){
                            removeData(position)
                            val jsonObject = JsonObject()
                            jsonObject.addProperty(Utils.task_id, model.id)
                            Utils.deleteTask(activity,jsonObject)
                        }
                    }
                } )

            })

            if (Utils.isLoadMore(position, todoList.size)) {
                pagination.onPage()
            }

        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    private fun removeData(position: Int) {
        todoList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, todoList.size)
    }
}

private class MyViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    val binding: ItemTaskBinding = ItemTaskBinding.bind(itemView)


}

