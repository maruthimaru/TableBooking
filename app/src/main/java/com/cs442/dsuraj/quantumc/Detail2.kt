package com.cs442.dsuraj.quantumc

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.cs442.dsuraj.quantumc.Detail2
import java.util.*

class Detail2 : Activity() {
    private var Output: TextView? = null
    private var changeDate: Button? = null
    private var year = 0
    private var month = 0
    private var day = 0
    var date: String? = null
    var dpd: DatePickerDialog? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)
        Output = findViewById<View>(R.id.tvDate) as TextView
        changeDate = findViewById<View>(R.id.btnChangeDate) as Button
        val tv = findViewById<View>(R.id.textView8) as TextView
        val img = findViewById<View>(R.id.imageView6) as ImageView
        val b = intent.extras
        val movie = b.getInt("booktype")
        if (movie == 1) {
            tv.text = "Round table"
            val myDrawable = resources.getDrawable(R.drawable.image1)
            img.setImageDrawable(myDrawable)
        } else if (movie == 2) {
            tv.text = "Meeting Table"
            val myDrawable = resources.getDrawable(R.drawable.front)
            img.setImageDrawable(myDrawable)
        } else if (movie == 3) {
            tv.text = "Premium Table"
            val myDrawable = resources.getDrawable(R.drawable.premium)
            img.setImageDrawable(myDrawable)
        } else if (movie == 4) {
            tv.text = "Celebrity Table"
            val myDrawable = resources.getDrawable(R.drawable.spiderman)
            img.setImageDrawable(myDrawable)
        }
        // Get current date by calender
        val c = Calendar.getInstance()
        year = c[Calendar.YEAR]
        month = c[Calendar.MONTH]
        day = c[Calendar.DAY_OF_MONTH]
        // Show current date
/*Output.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));*/
// Button listener to show date picker dialog
        changeDate!!.setOnClickListener {
            // On button click show datepicker dialog
            showDialog(DATE_PICKER_ID)
        }
        val btw = findViewById<View>(R.id.next) as Button
        btw.setOnClickListener {
            if (date != null) {
                val intent = Intent(this@Detail2, Theatre::class.java)
                intent.putExtra("movie_id", movie)
                intent.putExtra("date", date)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext, "Please Select the Date", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateDialog(id: Int): Dialog? {
        when (id) {
            DATE_PICKER_ID -> {
                val max = Calendar.getInstance()
                val c = Calendar.getInstance()
                val year = c[Calendar.YEAR]
                val month = c[Calendar.MONTH]
                val day = c[Calendar.DAY_OF_MONTH]
                dpd = DatePickerDialog(this, pickerListener, year, month, day)
                c.add(Calendar.DAY_OF_MONTH, 1)
                dpd!!.datePicker.minDate = c.timeInMillis
                max.add(Calendar.DAY_OF_MONTH, 50)
                dpd!!.datePicker.maxDate = max.timeInMillis
                return dpd as DatePickerDialog
            }
        }
        return null
    }

    private val pickerListener = OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
        // when dialog box is closed, below method will be called.
        year = selectedYear
        month = selectedMonth
        day = selectedDay
        date = Integer.toString(month + 1) + "/" + Integer.toString(day) + "/" + Integer.toString(year)
        // Show selected date
        Output!!.text = StringBuilder().append(month + 1)
                .append("-").append(day).append("-").append(year)
                .append(" ")
    }

    companion object {
        const val DATE_PICKER_ID = 1111
    }
}