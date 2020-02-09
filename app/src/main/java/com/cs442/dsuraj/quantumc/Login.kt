package com.cs442.dsuraj.quantumc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import layout.GPlusFragment

class Login : AppCompatActivity() {
    var gf = GPlusFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = GPlusFragment()
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
    }
}