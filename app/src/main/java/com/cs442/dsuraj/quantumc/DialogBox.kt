package com.cs442.dsuraj.quantumc

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DialogBox : AppCompatActivity() {
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_box)
        val intent = intent
        val amount = intent.getStringExtra("amount")
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.activity_user_information)
        val params = dialog.window.attributes
        // params.height = WindowManager.LayoutParams.FILL_PARENT;
        params.width = WindowManager.LayoutParams.FILL_PARENT
        dialog.window.attributes = params as WindowManager.LayoutParams
        val namecard = dialog.findViewById<View>(R.id.nameoncard) as EditText
        val creditcard = dialog.findViewById<View>(R.id.creditcard) as EditText
        val cvvno = dialog.findViewById<View>(R.id.cvv) as EditText
        val valid = dialog.findViewById<View>(R.id.vt1) as EditText
        val valid1 = dialog.findViewById<View>(R.id.vt2) as EditText
        val order = dialog.findViewById<View>(R.id.completeorder) as Button
        order.text = "Complete Order ( Total $amount)"
        // if button is clicked, close the custom dialog
        order.setOnClickListener {
            if (namecard.text.toString().isEmpty() || creditcard.text.toString().isEmpty() || cvvno.text.toString().isEmpty() || valid.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Please insert the maxTables", Toast.LENGTH_SHORT).show()
            } else {
                val intent: Intent
                intent = Intent(this@DialogBox, Confirmation::class.java)
                startActivity(intent)
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}