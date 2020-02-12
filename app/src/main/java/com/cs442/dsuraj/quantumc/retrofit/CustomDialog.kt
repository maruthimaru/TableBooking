package com.cs442.dsuraj.quantumc.retrofit

import android.app.ProgressDialog
import android.content.Context

class CustomDialog {
    lateinit var context:Context

    constructor(context: Context) {
        this.context = context
    }


    fun processDialog(): ProgressDialog {
        val showMe = ProgressDialog(context)
        showMe.setMessage("Please wait")
        showMe.setCancelable(true)
        showMe.show()
        return showMe
    }
}