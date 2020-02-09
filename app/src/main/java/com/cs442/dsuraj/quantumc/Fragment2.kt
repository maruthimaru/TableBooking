package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Fragment2 : Fragment(), View.OnClickListener {
    var btw2: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_tab3, container, false)
        btw2 = rootView.findViewById<View>(R.id.imageView20) as ImageView
        btw2!!.setOnClickListener(this)
        val share = rootView.findViewById<View>(R.id.imageButton20) as ImageButton
        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey wanna watch a movie, Mission Impossible, http://www.imdb.com/title/tt2381249/?ref_=nv_sr_1")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        return rootView
    }

    override fun onClick(view: View) {
        val intent = Intent(activity, Detail2::class.java)
        intent.putExtra("booktype", 2)
        startActivity(intent)
    }
}