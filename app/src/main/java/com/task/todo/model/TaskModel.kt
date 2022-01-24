package com.task.todo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TaskModel : Serializable {

    /**
     * status : 200
     * message : All tasks
     * data : {"total":20,"tasks":[{"id":1,"task":"Organize your to-do list","date":"2022-01-21","description":"Keep your to-do list organized in Asana. Group related tasks with sections and sort complex project task lists by date, assignee, or even custom fields."},{"id":3,"task":"Plan your day from anywhere","date":"2022-01-21","description":"Access everything you need to do for the day in My Tasks. Available on the web, desktop, and iOS or Android mobile devices, prioritize and plan your day on a simple user interface."},{"id":4,"task":"Never miss a deadline","date":"2022-01-21","description":"Set reminders and get notified when important tasks on your to-do list are approaching their due dates. With My Tasks, you can view project tasks assigned to you and create private tasks just for you so nothing slips through the cracks."},{"id":5,"task":"Organize your team to-do list and increase productivity","date":"2022-01-21","description":"Asana\u2019s project management tools and collaboration features make it easy to keep track of everything\u2014so you can focus on getting things done."},{"id":6,"task":"Start with a simple To-Do-List and add more Details as you go!","date":"2022-01-21","description":"Every big idea starts small. We help you to concentrate on the essentials. Add what you want to start, with the intuitive drag & drop you can re-organize and ad more details and tasks any time later."},{"id":7,"task":"Instant Team Collaboration!","date":"2022-01-21","description":"Send your team a TurboTask link and they can join immediately with just one click! All information is always up-to date and everyone is updated instantly. On Mobile and Desktop!"},{"id":8,"task":"Instant Messaging!","date":"2022-01-21","description":"Each of your Team-To-Do-List comes with a persistent chat room. Making your Teamwork faster, easier and more efficient!"},{"id":9,"task":"Task instantiation","date":"2022-01-21","description":"The following example creates and executes four tasks. Three tasks execute an Action<T> delegate named action, which accepts an argument of type Object."},{"id":10,"task":"Daily task list","date":"2022-01-21","description":"Stay organized with this accessible daily task list template. Important events, special occasions, and holidays get their own list, and daily to-do tasks keep you on track. Type a date in the yellow box to highlight tasks for that day."},{"id":11,"task":"Daily tracker template for tasks","date":"2022-01-21","description":"Whether at work or home - this template will help you easily start tracking your daily tasks and improve your time management so you can reach your goals."}]}
    </T> */
    @SerializedName("status")
    private var status = 0

    @SerializedName("message")
    private var message: String? = null

    /**
     * total : 20
     * tasks : [{"id":1,"task":"Organize your to-do list","date":"2022-01-21","description":"Keep your to-do list organized in Asana. Group related tasks with sections and sort complex project task lists by date, assignee, or even custom fields."},{"id":3,"task":"Plan your day from anywhere","date":"2022-01-21","description":"Access everything you need to do for the day in My Tasks. Available on the web, desktop, and iOS or Android mobile devices, prioritize and plan your day on a simple user interface."},{"id":4,"task":"Never miss a deadline","date":"2022-01-21","description":"Set reminders and get notified when important tasks on your to-do list are approaching their due dates. With My Tasks, you can view project tasks assigned to you and create private tasks just for you so nothing slips through the cracks."},{"id":5,"task":"Organize your team to-do list and increase productivity","date":"2022-01-21","description":"Asana\u2019s project management tools and collaboration features make it easy to keep track of everything\u2014so you can focus on getting things done."},{"id":6,"task":"Start with a simple To-Do-List and add more Details as you go!","date":"2022-01-21","description":"Every big idea starts small. We help you to concentrate on the essentials. Add what you want to start, with the intuitive drag & drop you can re-organize and ad more details and tasks any time later."},{"id":7,"task":"Instant Team Collaboration!","date":"2022-01-21","description":"Send your team a TurboTask link and they can join immediately with just one click! All information is always up-to date and everyone is updated instantly. On Mobile and Desktop!"},{"id":8,"task":"Instant Messaging!","date":"2022-01-21","description":"Each of your Team-To-Do-List comes with a persistent chat room. Making your Teamwork faster, easier and more efficient!"},{"id":9,"task":"Task instantiation","date":"2022-01-21","description":"The following example creates and executes four tasks. Three tasks execute an Action<T> delegate named action, which accepts an argument of type Object."},{"id":10,"task":"Daily task list","date":"2022-01-21","description":"Stay organized with this accessible daily task list template. Important events, special occasions, and holidays get their own list, and daily to-do tasks keep you on track. Type a date in the yellow box to highlight tasks for that day."},{"id":11,"task":"Daily tracker template for tasks","date":"2022-01-21","description":"Whether at work or home - this template will help you easily start tracking your daily tasks and improve your time management so you can reach your goals."}]
    </T> */
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
        @SerializedName("total")
        var total = 0

        /**
         * id : 1
         * task : Organize your to-do list
         * date : 2022-01-21
         * description : Keep your to-do list organized in Asana. Group related tasks with sections and sort complex project task lists by date, assignee, or even custom fields.
         */
        @SerializedName("tasks")
        var tasks: List<TasksBean>? = null

        class TasksBean : Serializable {
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

}