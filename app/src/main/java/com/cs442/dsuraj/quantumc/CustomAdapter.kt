package com.cs442.dsuraj.quantumc

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import java.util.*

class CustomAdapter(private val context: Activity, moviereview: ArrayList<TableReview>?) : BaseAdapter() {
    private var moviereview: ArrayList<TableReview>? = null
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return moviereview!!.size
    }

    override fun getItem(position: Int): Any {
        return moviereview!![position]
    }

    //set the text and image in the display to the position value from arraylist and set text and image to list
    override fun getView(position: Int, view: View, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.layoutreview, null)
        val imageView = rowView.findViewById<View>(R.id.img) as ImageView
        val res = context.resources
        val resID = res.getIdentifier(moviereview!![position].getmovieimage(), "drawable", context.packageName)
        imageView.setImageResource(resID)
        val textid = rowView.findViewById<View>(R.id.textid) as TextView
        textid.text = moviereview!![position].getuser()
        val textname = rowView.findViewById<View>(R.id.textname) as TextView
        textname.text = moviereview!![position].getmoviename()
        val textrate = rowView.findViewById<View>(R.id.textrate) as RatingBar
        val rate = moviereview!![position].getmovierating()
        textrate.numStars = rate
        return rowView
    }

    init {
        this.moviereview = moviereview
    }
}