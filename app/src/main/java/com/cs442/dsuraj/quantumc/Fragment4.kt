package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Fragment4 : Fragment(), View.OnClickListener {
    var btw4: ImageView? = null
    var img1: ImageButton? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_tab5, container, false)
        btw4 = rootView.findViewById<View>(R.id.imageView40) as ImageView
        btw4!!.setOnClickListener(this)
        img1 = rootView.findViewById<View>(R.id.imageButton40) as ImageButton
        img1!!.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey wanna watch a movie, The Amazing Spider-Man 2 (2014), http://www.imdb.com/title/tt1872181/?ref_=nv_sr_3")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        return rootView
    }

    override fun onClick(view: View) {
        val intent = Intent(activity, Detail2::class.java)
        intent.putExtra("booktype", 4)
        startActivity(intent)
    }
}