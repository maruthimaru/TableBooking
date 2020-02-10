package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Fragment1 : Fragment(), View.OnClickListener {
    lateinit var rootView: View
    var btw1: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_tab2, container, false)
        btw1 = rootView.findViewById<View>(R.id.imageView) as ImageView
        btw1!!.setOnClickListener(this)
        val button = rootView.findViewById<View>(R.id.imageButton2) as ImageButton
        button.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey wanna watch a movie, Fly Paper, http://www.imdb.com/title/tt1541160/")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        return rootView
    }

    override fun onClick(view: View) {
        val intent = Intent(activity, Detail2::class.java)
        intent.putExtra("booktype", 1)
        startActivity(intent)
        activity!!.finish()
    }
}