package com.cs442.dsuraj.quantumc

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.cs442.dsuraj.quantumc.TableConfirmation
import com.cs442.dsuraj.quantumc.db.AppDatabase
import com.cs442.dsuraj.quantumc.db.dao.MovieBookedDao
import com.cs442.dsuraj.quantumc.db.table.MoviesBooked
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

class Confirmation : Activity() {
    var arr = ArrayList<String?>()
    val context: Context = this
    var sql: SQLiteDatabase? = null
    var db: DatabaseHelper? = null
    var maximum = 0
    var sum = 0
    var sum1 = 0
    var mul = 0
    var total_price = 0
    var tax_price = 0
    var email: EditText? = null
    var phone: EditText? = null
    lateinit var appDatabase:AppDatabase
    lateinit var movieBookedDao: MovieBookedDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)
        db = DatabaseHelper(applicationContext)
        sql = db!!.writableDatabase
        val toast = Toast.makeText(applicationContext, "Confirmation Page", Toast.LENGTH_LONG)
        val intent = intent
        val seat_info = intent.getBundleExtra("seat_information")
        val movie_id = intent.getStringExtra("movie_id")
        val date1 = intent.getStringExtra("date")
        val time1 = intent.getStringExtra("time")
        val theatre1 = intent.getStringExtra("theatre")
        val seating = seat_info.getStringArrayList("seats")
        val total = seat_info.getStringArrayList("total")
        appDatabase= AppDatabase.getDatabase(this)
        movieBookedDao=appDatabase.movieBooking()
        var movie_name: String? = null
        print(movie_id)
        val seat_number = total[0]
        println("The seat number value is$seat_number")
        total_price = seat_number.toInt() * 100
        tax_price = seat_number.toInt() * 10
        val price_final = resources.getString(R.string.Rs) + Integer.toString(total_price) + " + " + resources.getString(R.string.Rs) + tax_price
        val movie = db!!.getMoviename(sql!!, movie_id)
        while (movie.moveToNext()) {
            movie_name = movie.getString(0)
        }
        arr.add(0, movie_name)
        val len = seating.size
        arr.add(1, theatre1)
        arr.add(2, date1)
        arr.add(3, time1)
        arr.add(4, price_final)
        val seats = findViewById<View>(R.id.noofseats) as TextView
        val theatre = findViewById<View>(R.id.theascr) as TextView
        val date = findViewById<View>(R.id.date) as TextView
        val time = findViewById<View>(R.id.time) as TextView
        val price = findViewById<View>(R.id.price) as TextView
        email = findViewById<View>(R.id.emailid) as EditText
        phone = findViewById<View>(R.id.phone) as EditText
        val styledString = SpannableString(arr[0])
        styledString.setSpan(StyleSpan(Typeface.BOLD), 0, arr[0]!!.length, 0)
        val tv = findViewById<View>(R.id.textView16) as TextView
        tv.text = styledString
        val imageview = findViewById<View>(R.id.imageView3) as ImageView
        when (movie_id.toInt()) {
            1 -> imageview.setImageResource(R.drawable.image1)
            2 -> imageview.setImageResource(R.drawable.front)
            3 -> imageview.setImageResource(R.drawable.premium)
            4 -> imageview.setImageResource(R.drawable.spiddy)
        }
        seats.text = ""
        for (i in 0 until len) {
            seats.text = seats.text.toString() + seating[i]
            if (i != len - 1) {
                seats.text = seats.text.toString() + ","
            }
        }
        theatre.text = arr[1]
        time.text = arr[3]
        date.text = arr[2]
        price.text = arr[4]
        //Passing info to TableConfirmation Screen
        val bundle = Bundle()
        bundle.putString("movie_id", movie_id)
        bundle.putString("date", date1)
        bundle.putBundle("seat_information", seat_info)
        bundle.putString("time", time1)
        bundle.putString("theatre", theatre1)
        bundle.putString("price", price.text.toString())
        val bktkt = findViewById<View>(R.id.pay) as Button
        bktkt.setOnClickListener {
            if (!isValidEmaillId(email!!.text.toString().trim { it <= ' ' }) || phone!!.text.toString().length != 10) {
                Toast.makeText(applicationContext, "Email/Phone number not valid", Toast.LENGTH_SHORT).show()
            } else {
                val Email = email!!.text.toString()
                val phoneno = phone!!.text.toString()
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
                order.text = "Complete Order ( Total " + arr[4] + ")"
                // if button is clicked, close the custom dialog
                order.setOnClickListener {
                    val reversedarry = ArrayList<String>()
                    if (namecard.text.toString().isEmpty() || creditcard.text.toString().isEmpty() || cvvno.text.toString().isEmpty() || valid.text.toString().isEmpty()) {
                        Toast.makeText(applicationContext, "Please insert the data", Toast.LENGTH_SHORT).show()
                    } else {
                        val booking_id = 0
                        Log.d("phone no", phoneno)
                        maximum=movieBookedDao.getMax()
                        Log.e(" maximum : " , ""+maximum)
//                        val max = db!!.getmaxbooking(sql!!)
//                        if (max != null && max.count > 0) {
//                            Log.e("max : ", ""+max.count)
//                            while (max.moveToNext()) {
//                                maximum = max.getInt(0)
//                            }
//                        }

                        val movieList=ArrayList<MoviesBooked>()
                        movieList.add(MoviesBooked(movie_id, time1,theatre1,Email,10, date1, phoneno,seats.text.toString()))

                        movieBookedDao.insert(movieList)
                        if (db!!.insertmoviebooked(movie_id.toInt(), seats.text.toString(), theatre1, time1, date1, 10, Email, phoneno)) {
                            val intent: Intent
                            intent = Intent(context, TableConfirmation::class.java)
                            intent.putExtra("booking_id", maximum + 1)
                            intent.putExtra("amount", price_final)
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext, "Error in Movie Booking. Please try again", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                dialog.show()
            }
        }
    }

    private fun isValidEmaillId(email: String): Boolean {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches()
    }
}