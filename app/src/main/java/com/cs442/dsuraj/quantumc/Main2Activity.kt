package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val animation: Animation
        animation = AnimationUtils.loadAnimation(applicationContext,
                R.anim.animated)
        val animation1: Animation
        animation1 = AnimationUtils.loadAnimation(applicationContext,
                R.anim.fade_in)
        val q = findViewById<View>(R.id.imageView) as ImageView
        val text = findViewById<View>(R.id.textView9) as TextView
        q.startAnimation(animation)
        text.startAnimation(animation1)
        val task: TimerTask = object : TimerTask() {
            override fun run() { // TODO Auto-generated method stub
                val intent = Intent(this@Main2Activity, MainActivity::class.java)
                startActivity(intent)
                finishscreen()
            }
        }
        val t = Timer()
        t.schedule(task, 3000)
    }

    private fun finishscreen() {
        finish()
    }
}