package com.task.todo.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.task.todo.R
import com.task.todo.databinding.ActivitySplashBinding
import com.task.todo.utils.MyPreferences

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
        Handler().postDelayed({
            startActivity(Intent(activity, MainActivity::class.java))
            finish()
        }, 2000)

    }
}