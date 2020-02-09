package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Fragment3 : Fragment(), View.OnClickListener {
    var btw3: ImageView? = null
    var img: ImageButton? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_tab4, container, false)
        btw3 = rootView.findViewById<View>(R.id.imageView30) as ImageView
        btw3!!.setOnClickListener(this)
        img = rootView.findViewById<View>(R.id.imageButton30) as ImageButton
        img!!.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey wanna watch a movie, Avengers, http://www.imdb.com/title/tt2395427/?ref_=nv_sr_2")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        return rootView
    }

    override fun onClick(view: View) {
        val intent = Intent(activity, Detail2::class.java)
        intent.putExtra("booktype", 3)
        startActivity(intent)
    }
}