package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class Theatre1 : AppCompatActivity() {
    var dist = 0
    var distance1 = 0
    var amcdist: TextView? = null
    var regaldist: TextView? = null
    var landmark: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theatre1)
        val b = intent.extras
        val movie = b.getInt("movie_id")
        val date = b.getString("date")
        val a1 = findViewById<View>(R.id.button9) as Button
        val AMC2 = findViewById<View>(R.id.AMC2) as Button
        val AMC3 = findViewById<View>(R.id.AMC3) as Button
        val AMC4 = findViewById<View>(R.id.AMC4) as Button
        val AMC5 = findViewById<View>(R.id.AMC5) as Button
        val RC1 = findViewById<View>(R.id.RC1) as Button
        val RC2 = findViewById<View>(R.id.RC2) as Button
        val RC3 = findViewById<View>(R.id.RC3) as Button
        val RC4 = findViewById<View>(R.id.RC4) as Button
        val LMC1 = findViewById<View>(R.id.LMC1) as Button
        val LMC2 = findViewById<View>(R.id.LMC2) as Button
        val LMC3 = findViewById<View>(R.id.LMC3) as Button
        val LMC4 = findViewById<View>(R.id.LMC4) as Button
        val LMC5 = findViewById<View>(R.id.LMC5) as Button
        val LMC9 = findViewById<View>(R.id.LMC9) as Button
        val LMC6 = findViewById<View>(R.id.LMC6) as Button
        val LMC10 = findViewById<View>(R.id.LMC10) as Button
        val LMC7 = findViewById<View>(R.id.LMC7) as Button
        val LMC8 = findViewById<View>(R.id.LMC8) as Button
        val LMC15 = findViewById<View>(R.id.LMC15) as Button
        val LMC12 = findViewById<View>(R.id.LMC12) as Button
        val LMC16 = findViewById<View>(R.id.LMC16) as Button
        val LMC13 = findViewById<View>(R.id.LMC13) as Button
        val LMC14 = findViewById<View>(R.id.LMC14) as Button

        amcdist = findViewById<View>(R.id.amcdist) as TextView
        regaldist = findViewById<View>(R.id.regaldist) as TextView
        landmark = findViewById<View>(R.id.landmarkdist) as TextView
        distance1 = distance(41.891000, -87.620100)
        if (distance1 == 0) {
            Toast.makeText(applicationContext, "Please Switch on GPS to find the Theatre Distance", Toast.LENGTH_SHORT).show()
            amcdist!!.text = Integer.toString(distance1) + " mi"
            distance1 = distance(41.751352, -87.583533)
            regaldist!!.text = Integer.toString(distance1) + " mi"
            distance1 = distance(41.933400, -87.645600)
            landmark!!.text = Integer.toString(distance1) + " mi"
            val swipe = findViewById<View>(R.id.activity_theatre) as SwipeRefreshLayout
            swipe.setOnRefreshListener {
                swipe.isRefreshing = true
                Handler().postDelayed({
                    swipe.isRefreshing = false
                    distance1 = distance(41.891000, -87.620100)
                    amcdist!!.text = Integer.toString(distance1) + " mi"
                    distance1 = distance(41.751352, -87.583533)
                    regaldist!!.text = Integer.toString(distance1) + " mi"
                    distance1 = distance(41.933400, -87.645600)
                    landmark!!.text = Integer.toString(distance1) + " mi"
                }, 2000)
            }
        } else {
            amcdist!!.text = Integer.toString(distance1) + " mi"
            distance1 = distance(41.751352, -87.583533)
            regaldist!!.text = Integer.toString(distance1) + " mi"
            distance1 = distance(41.933400, -87.645600)
            landmark!!.text = Integer.toString(distance1) + " mi"
        }
        a1.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)

            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Barbeque Nation")
            intent.putExtra("time", "11:55 AM")
            startActivity(intent)
        }
        AMC2.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Barbeque Nation")
            intent.putExtra("time", "02.15 PM")
            startActivity(intent)
        }
        AMC3.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Barbeque Nation")
            intent.putExtra("time", "05.30 PM")
            startActivity(intent)
        }
        AMC4.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Barbeque Nation")
            intent.putExtra("time", "07.15 PM")
            startActivity(intent)
        }
        AMC5.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Barbeque Nation")
            intent.putExtra("time", "09.15 PM")
            startActivity(intent)
        }
        RC1.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Bird on Tree")
            intent.putExtra("time", "12.05 PM")
            startActivity(intent)
        }
        RC2.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Bird on Tree")
            intent.putExtra("time", "04.15 PM")
            startActivity(intent)
        }
        RC3.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Bird on Tree")
            intent.putExtra("time", "07.05 PM")
            startActivity(intent)
        }
        RC4.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Bird on Tree")
            intent.putExtra("time", "19.15 PM")
            startActivity(intent)
        }
        LMC1.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Lemon tree")
            intent.putExtra("time", "11.30 PM")
            startActivity(intent)
        }
        LMC2.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Lemon tree")
            intent.putExtra("time", "02.45 PM")
            startActivity(intent)
        }
        LMC3.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Lemon tree")
            intent.putExtra("time", "04.30 PM")
            startActivity(intent)
        }
        LMC4.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Lemon tree")
            intent.putExtra("time", "07.05 PM")
            startActivity(intent)
        }
        LMC5.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Lemon tree")
            intent.putExtra("time", "09.15 PM")
            startActivity(intent)
        }

        LMC9.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "The Saravana bavan")
            intent.putExtra("time", "11:30 PM")
            startActivity(intent)
        }
        LMC6.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "The Saravana bavan")
            intent.putExtra("time", "2:45 PM")
            startActivity(intent)
        }
        LMC10.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "The Saravana bavan")
            intent.putExtra("time", "4:30 PM")
            startActivity(intent)
        }
        LMC7.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "The Saravana bavan")
            intent.putExtra("time", "7:05 PM")
            startActivity(intent)
        }
        LMC8.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "The Saravana bavan")
            intent.putExtra("time", "9:15 PM")
            startActivity(intent)
        }
        LMC15.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Haritage inn")
            intent.putExtra("time", "11:30 PM")
            startActivity(intent)
        }
        LMC12.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Haritage inn")
            intent.putExtra("time", "2:45 PM")
            startActivity(intent)
        }
        LMC16.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Haritage inn")
            intent.putExtra("time", "4:30 PM")
            startActivity(intent)
        }
        LMC13.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Haritage inn")
            intent.putExtra("time", "7:05 PM")
            startActivity(intent)
        }
        LMC14.setOnClickListener {
            val intent: Intent
            intent = if (movie == 1) {
                Intent(this@Theatre1, SeatSelectionRound::class.java)
            } else {
                Intent(this@Theatre1, SeatSelection::class.java)
            }
            intent.putExtra("movie_id", movie)
            intent.putExtra("date", date)
            intent.putExtra("theatre", "Haritage inn")
            intent.putExtra("time", "09.15 PM")
            startActivity(intent)
        }

    }

    fun distance(latitude: Double, longitude: Double): Int {
        val gps = GPSTracker(this)
        if (gps.canGetLocation()) { // gps enabled} // return boolean true/false
            val lat2 = gps.getLatitude() // returns latitude
            val lng2 = gps.getLongitude() // returns longitude
            print("$lat2 $lng2")
            gps.stopUsingGPS()
            //    Toast.makeText(getApplicationContext(), "Your location" + lat2 + " \n" + lng2, Toast.LENGTH_SHORT).show();
            val earthRadius = 3958.75
            val dLat = Math.toRadians(latitude - lat2)
            val dLng = Math.toRadians(longitude - lng2)
            val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(latitude)) *
                    Math.sin(dLng / 2) * Math.sin(dLng / 2)
            val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
            dist = (earthRadius * c).toInt()
            // Toast.makeText(getApplicationContext(), "Your distance" + dist, Toast.LENGTH_LONG).show();
        }
        return dist
    } /*Intent intent = new Intent(Theatre.this, SeatSelection.class);
        intent.putExtra("movie_id", movie);
        intent.putExtra("date", date);*/
}