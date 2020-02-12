package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs442.dsuraj.quantumc.db.AppDatabase
import com.cs442.dsuraj.quantumc.db.JSONArrayCursor
import com.cs442.dsuraj.quantumc.db.dao.MovieBookedDao
import com.cs442.dsuraj.quantumc.db.table.DataResponse
import com.cs442.dsuraj.quantumc.db.table.SeatDetailResponse
import com.cs442.dsuraj.quantumc.db.table.SeatRequest
import com.cs442.dsuraj.quantumc.retrofit.CustomDialog
import com.cs442.dsuraj.quantumc.retrofit.Utils
import com.google.gson.Gson
import com.scoto.visitormanagent.retrofit.ApiService
import com.scoto.visitormanagent.retrofit.RetrofitClientInstance
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class SeatSelectionRound : AppCompatActivity() {
    private lateinit var seating: String
    var j = 0
    private lateinit var jSONArrayCursor: Cursor
    lateinit var customDialog:CustomDialog
    var arrayList = ArrayList<String?>()
    var arrayList1 = ArrayList<String?>()
    lateinit var sql: SQLiteDatabase
    var db: DatabaseHelper? = null
    var outer = ArrayList<ArrayList<String?>>()
    var seats_counter = 0
    var A1: Button? = null
    var A2: Button? = null
    var A3: Button? = null
    var A4: Button? = null
    var A5: Button? = null
    var A6: Button? = null
    var A7: Button? = null
    var A8: Button? = null
    var A9: Button? = null
    var A10: Button? = null
    var B1: Button? = null
    var B2: Button? = null
    var B3: Button? = null
    var B4: Button? = null
    var B5: Button? = null
    var B6: Button? = null
    var B7: Button? = null
    var B8: Button? = null
    var C1: Button? = null
    var C2: Button? = null
    var C3: Button? = null
    var C4: Button? = null
    var C5: Button? = null
    var C6: Button? = null
    var C7: Button? = null
    var C8: Button? = null
    var D1: Button? = null
    var D2: Button? = null
    var D3: Button? = null
    var D4: Button? = null
    var D5: Button? = null
    var D6: Button? = null
    var D7: Button? = null
    var D8: Button? = null
    var temp: String? = null
    var temp1: String? = null
    var colorid = 0
    var flag = 0
    var bundle: Bundle? = null
    var menuItemModelArrayList = ArrayList<MenuItemModel>()
    lateinit var recyclerView: RecyclerView
    var movie = 0
    lateinit var date: String
    lateinit var theatre: String
    lateinit var time: String
    lateinit var appDatabase: AppDatabase
    lateinit var movieBookedDao: MovieBookedDao
    var seats: String? = null
    private val TAG = SeatSelectionRound::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection_round)
        db = DatabaseHelper(applicationContext)
        customDialog= CustomDialog(this)
        appDatabase= AppDatabase.getDatabase(this)
        movieBookedDao=appDatabase.movieBooking()
        sql = db!!.readableDatabase
        recyclerView = findViewById(R.id.recyclerView)
        A1 = findViewById<View>(R.id.A1) as Button
        A2 = findViewById<View>(R.id.A2) as Button
        A3 = findViewById<View>(R.id.A3) as Button
        A4 = findViewById<View>(R.id.A4) as Button
        A5 = findViewById<View>(R.id.A5) as Button
        A6 = findViewById<View>(R.id.A6) as Button
        B1 = findViewById<View>(R.id.B1) as Button
        B2 = findViewById<View>(R.id.B2) as Button
        B3 = findViewById<View>(R.id.B3) as Button
        B4 = findViewById<View>(R.id.B4) as Button
        C1 = findViewById<View>(R.id.C1) as Button
        C2 = findViewById<View>(R.id.C2) as Button
        C3 = findViewById<View>(R.id.C3) as Button
        C4 = findViewById<View>(R.id.C4) as Button
        D1 = findViewById<View>(R.id.D1) as Button
        D2 = findViewById<View>(R.id.D2) as Button
        D3 = findViewById<View>(R.id.D3) as Button
        D4 = findViewById<View>(R.id.D4) as Button
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true))
        val b = intent.extras
        movie = b.getInt("movie_id")
        date = b.getString("date")
        theatre = b.getString("theatre")
        time = b.getString("time")
        // while should encompass the below code and forloop
        seating = ""
        print(theatre)
        menuItemModelArrayList = ArrayList()
        menuItemModelArrayList.add(MenuItemModel("Chicken briyani", R.drawable.chicken_biryani_recipe))
        menuItemModelArrayList.add(MenuItemModel("Egg fride rice", R.drawable.egg_fried_rice))
        menuItemModelArrayList.add(MenuItemModel("Veg briyani", R.drawable.veg_biryani_recipe))
        menuItemModelArrayList.add(MenuItemModel("curd rice", R.drawable.curd_rice))
        menuItemModelArrayList.add(MenuItemModel("Dosa", R.drawable.dosa))
        menuItemModelArrayList.add(MenuItemModel("Idly", R.drawable.idly))
        val adapter = MenuItemAdapter(this, menuItemModelArrayList)
        recyclerView.setAdapter(adapter)
        val seatnos =movieBookedDao.getseats(theatre!!, date!!, time!!, movie)
        val seatObj= SeatRequest(movie.toString(),theatre!!,time!!,date!!)
//        val seatnos = db!!.getseats(sql, theatre, date, time, movie)

        cursorProcess(seatnos)

        getseats(seatObj)

        A1!!.setOnClickListener {
            val buttonColor = A1!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                println("I am in selected seat")
                A1!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("A1")
                // Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
// toast.show();
//    System.out.println("The seats are :"+ arrayList);
            } else if (colorid == -6005925) {
                println("This seat is already booked")
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_SHORT)
                toast.show()
            } else if (colorid == -7886485) {
                println("This seat is unselected")
                //  Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//  toast.show();
                A1!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("A1")
            }
        }
        A2!!.setOnClickListener {
            val buttonColor = A2!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                A2!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("A2")
                //   Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//   toast.show();
                println("The seats are :$arrayList")
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_SHORT)
                toast.show()
            } else if (colorid == -7886485) { //    Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//    toast.show();
                A2!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("A2")
            }
        }
        A3!!.setOnClickListener {
            val buttonColor = A3!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                A3!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("A3")
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_SHORT)
                toast.show()
            } else if (colorid == -7886485) {
                A3!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("A3")
            }
        }
        A4!!.setOnClickListener {
            val buttonColor = A4!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                A4!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("A4")
                //                    Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//                    toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //   Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//   toast.show();
                A4!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("A4")
            }
        }
        A5!!.setOnClickListener {
            val buttonColor = A5!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                A5!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("A5")
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_SHORT)
                toast.show()
            } else if (colorid == -7886485) {
                A5!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("A5")
            }
        }
        A6!!.setOnClickListener {
            val buttonColor = A6!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                A6!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("A6")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_SHORT)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                A6!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("A6")
            }
        }
        B1!!.setOnClickListener {
            val buttonColor = B1!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                B1!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("B1")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                B1!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("B1")
            }
        }
        B2!!.setOnClickListener {
            val buttonColor = B2!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                B2!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("B2")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                B2!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("B2")
            }
        }
        B3!!.setOnClickListener {
            val buttonColor = B3!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                B3!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("B3")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                B3!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("B3")
            }
        }
        B4!!.setOnClickListener {
            val buttonColor = B4!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                B4!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("B4")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                B4!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("B4")
            }
        }
        C1!!.setOnClickListener {
            val buttonColor = C1!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                C1!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("C1")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                C1!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("C1")
            }
        }
        C2!!.setOnClickListener {
            val buttonColor = C2!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                C2!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("C2")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                C2!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("C2")
            }
        }
        C3!!.setOnClickListener {
            val buttonColor = C3!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                C3!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("C3")
                // Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                C3!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("C3")
            }
        }
        C4!!.setOnClickListener {
            val buttonColor = C4!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                C4!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("C4")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                C4!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("C4")
            }
        }
        D1!!.setOnClickListener {
            val buttonColor = D1!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                D1!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("D1")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                D1!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("D1")
            }
        }
        D2!!.setOnClickListener {
            val buttonColor = D2!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                D2!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("D2")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                D2!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("D2")
            }
        }
        D3!!.setOnClickListener {
            val buttonColor = D3!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                D3!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("D3")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_LONG);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                D3!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("D3")
            }
        }
        D4!!.setOnClickListener {
            val buttonColor = D4!!.background as ColorDrawable
            colorid = buttonColor.color
            if (colorid == -1654272) {
                D4!!.setBackgroundColor(Color.parseColor("#87A96B"))
                seats_counter++
                arrayList.add("D4")
                //Toast toast= Toast.makeText(getApplicationContext(),"You have selected this seat.",Toast.LENGTH_SHORT);
//toast.show();
            } else if (colorid == -6005925) {
                val toast = Toast.makeText(applicationContext, "Sorry, this seat is already booked.", Toast.LENGTH_LONG)
                toast.show()
            } else if (colorid == -7886485) { //Toast toast= Toast.makeText(getApplicationContext(),"You have unselected this seat.",Toast.LENGTH_LONG);
//toast.show();
                D4!!.setBackgroundColor(Color.parseColor("#E6C200"))
                seats_counter--
                arrayList.remove("D4")
            }
        }
        //  temp=Integer.toString(seats_counter);
// arrayList.add(temp);
    }

    private fun getseats(seatRequest: SeatRequest){
        Log.e(TAG," send list " +Gson().toJson(seatRequest))
//        alertDialog = customDialog.loading(activity!!)
        val showme=customDialog.processDialog()
        val service = RetrofitClientInstance.createServices(ApiService::class.java, Utils.baseUrl)
        val listCall = service.getSeat(seatRequest)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<SeatDetailResponse> {
            override fun onFailure(call: Call<SeatDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                showme.dismiss()
            }
            override fun onResponse(call: Call<SeatDetailResponse>, response: retrofit2.Response<SeatDetailResponse>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.body() != null) {
                    Log.e(TAG, "onResponse : " + Gson().toJson(response.body()!!))
                    if (response.body()!!.status!!) {
                        val jsArray = JSONArray(Gson().toJson(response.body()!!.data));
                        Log.e(TAG, "onResponse : " + jsArray)
                        jSONArrayCursor = JSONArrayCursor(jsArray)
                        cursorProcess(jSONArrayCursor)
                    }else{
                        Toast.makeText(applicationContext,response.body()!!.message!!,Toast.LENGTH_SHORT).show()
                    }
                        showme.dismiss()

                }else{
                    showme.dismiss()
                }
            }

        })
    }

    private fun cursorProcess(seatnos: Cursor) {
        print("   " + seatnos!!.count)
        if (seatnos != null && seatnos.count > 0) {
            print(seating)
            while (seatnos.moveToNext()) {
                if (j == 0) {
                    seating = seatnos.getString(0)
                } else if (seatnos.count > 1) {
                    seating = "$seating,"
                    seating = if (!seatnos.isLast) {
                        seating + seatnos.getString(0)
                    } else {
                        seating + seatnos.getString(0)
                    }
                }
                j++
            }
            Log.e(TAG, "onCreate: $seating")
            print("fassdfsdsdfssdfssdf $seating")
            val seats_booked = Arrays.asList(*seating.split(",").toTypedArray())
            for (i in seats_booked.indices) {
                println("Split String: " + seats_booked[i])
                if ("A1" == seats_booked[i]) {
                    A1!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("A2" == seats_booked[i]) {
                    A2!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("A3" == seats_booked[i]) {
                    A3!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("A4" == seats_booked[i]) {
                    A4!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("A5" == seats_booked[i]) {
                    A5!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("A6" == seats_booked[i]) {
                    A6!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("B1" == seats_booked[i]) {
                    B1!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("B2" == seats_booked[i]) {
                    B2!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("B3" == seats_booked[i]) {
                    B3!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("B4" == seats_booked[i]) {
                    B4!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("C1" == seats_booked[i]) {
                    C1!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("C2" == seats_booked[i]) {
                    C2!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("C3" == seats_booked[i]) {
                    C3!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("C4" == seats_booked[i]) {
                    C4!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("D1" == seats_booked[i]) {
                    D1!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("D2" == seats_booked[i]) {
                    D2!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("D3" == seats_booked[i]) {
                    D3!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                } else if ("D4" == seats_booked[i]) {
                    D4!!.setBackgroundColor(Color.parseColor("#A45B5B"))
                }
            }
            // }
        } else {
            print("no print")
        }
    }

    fun counter() {
        val size: Int
        if (flag == 0) {
            temp = Integer.toString(seats_counter)
            arrayList1.add(temp)
            outer.add(arrayList1)
            flag = 1
        } else {
            size = arrayList1.size
            val get_currval = arrayList1[size - 1]
            arrayList1.remove(get_currval)
            temp = Integer.toString(seats_counter)
            arrayList1.add(temp)
            outer.add(arrayList1)
            //   System.out.println("Seat Count value changed:"+ temp);
        }
    }

    fun seat_information(V: View?) {
        val intent: Intent
        outer.clear()
        outer.add(arrayList)
        counter()
        if (seats_counter != 0) {
            bundle = Bundle()
            bundle!!.putStringArrayList("seats", arrayList)
            bundle!!.putStringArrayList("total", arrayList1)
            println("The arraylist items are :$outer")
            println("The movie is : $movie")
            println("The date is: $date")
            intent = Intent(this@SeatSelectionRound, Confirmation::class.java)
            intent.putExtra("seat_information", bundle)
            intent.putExtra("movie_id", Integer.toString(movie))
            intent.putExtra("date", date)
            intent.putExtra("theatre", theatre)
            intent.putExtra("time", time)
            startActivity(intent)
        } else {
            val toast = Toast.makeText(applicationContext, "Please select a seat to proceed", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}