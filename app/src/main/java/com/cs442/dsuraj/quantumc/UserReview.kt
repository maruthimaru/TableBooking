package com.cs442.dsuraj.quantumc

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Created by sushma on 11/24/2016.
 */
class UserReview : AppCompatActivity() {
    lateinit var moviereview: ArrayList<TableReview>
    var movielist: ListView? = null
    var adapter: CustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review)
        //create Array list of menu items
        moviereview = ArrayList<TableReview>()
        moviereview.add(TableReview("Avenger", "@drawable/avengerreview", 5, "Excellent"))
        moviereview.add(TableReview("Flypaper", "@drawable/flypaperreview", 3, "Good"))
        moviereview.add(TableReview("Mission Impossible", "@drawable/missionimpossible", 2, "Not Good"))
        moviereview.add(TableReview("Spiderman", "@drawable/spidermanreview", 1, "Worst Movie Ever"))
        adapter = CustomAdapter(this, moviereview)
        movielist = findViewById<View>(R.id.myListView) as ListView
        movielist!!.adapter = adapter
    }
}