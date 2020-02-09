package com.cs442.dsuraj.quantumc

import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Created by sushma on 11/23/2016.
 */
class ComingSoon : AppCompatActivity() {
    lateinit var moviedetails: ArrayList<TableDetails>
    var movielist: ListView? = null
    var currentimageindex = 0
    var slidingimage: ImageView? = null
    var txtdata: TextView? = null
    var textname: TextView? = null
    var longitude = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layoutcomingsoon)
        //create Array list of menu items
        moviedetails = ArrayList<TableDetails>()
        moviedetails.add(TableDetails("Bahubaali", "@drawable/bahu", "https://www.youtube.com/watch?v=bICA1ZtU6ck"))
        moviedetails.add(TableDetails("Dangal", "@drawable/dangal", "https://www.youtube.com/watch?v=x_7YlGv9u1g"))
        moviedetails.add(TableDetails("Raees", "@drawable/raees", "https://www.youtube.com/watch?v=8iv3ksZs0hk"))
        moviedetails.add(TableDetails("sarkar", "@drawable/sarkar", "https://www.youtube.com/watch?v=ea9eX2QnnV0"))
        slidingimage = findViewById<View>(R.id.imgcmgsoon) as ImageView
        textname = findViewById<View>(R.id.txtmovie) as TextView
        txtdata = findViewById<View>(R.id.txtdata) as TextView
        val mHandler = Handler()
        // Create runnable for posting
        val mUpdateResults = Runnable { AnimateandSlideShow() }
        val delay = 1000 // delay for 1 sec.
        val period = 5000 // repeat every 4 sec.
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                mHandler.post(mUpdateResults)
            }
        }, 0, period.toLong())
    }

    fun AnimateandSlideShow() {
        print(slidingimage)
        val res = this.resources
        val resID = res.getIdentifier(moviedetails!![currentimageindex % moviedetails!!.size]!!.getmovieimage(), "drawable", this.packageName)
        slidingimage!!.setImageResource(resID)
        textname!!.text = moviedetails!![currentimageindex % moviedetails!!.size]!!.getmoviename()
        txtdata!!.movementMethod = LinkMovementMethod.getInstance()
        txtdata!!.text = Html.fromHtml("<a href='" + moviedetails!![currentimageindex % moviedetails!!.size]!!.getmoviedata() + "'> Watch Trailer</a>")
        currentimageindex++
        val rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        txtdata!!.startAnimation(rotateimage)
        slidingimage!!.startAnimation(rotateimage)
        textname!!.startAnimation(rotateimage)
    }
}