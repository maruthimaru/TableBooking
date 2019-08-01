package com.cs442.dsuraj.quantumc;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sushma on 11/23/2016.
 */

public class ComingSoon extends AppCompatActivity {
    ArrayList<TableDetails> moviedetails;
    ListView movielist;
    public int currentimageindex=0;
    ImageView slidingimage;
    TextView txtdata;
    TextView textname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutcomingsoon);

        //create Array list of menu items
        moviedetails = new ArrayList();

        moviedetails.add(new TableDetails("Bahubaali","@drawable/bahu","https://www.youtube.com/watch?v=bICA1ZtU6ck"));

        moviedetails.add(new TableDetails("Dangal","@drawable/dangal","https://www.youtube.com/watch?v=x_7YlGv9u1g"));

        moviedetails.add(new TableDetails("Raees","@drawable/raees","https://www.youtube.com/watch?v=8iv3ksZs0hk"));

        moviedetails.add(new TableDetails("sarkar","@drawable/sarkar","https://www.youtube.com/watch?v=ea9eX2QnnV0"));



        slidingimage = (ImageView) findViewById(R.id.imgcmgsoon);

        textname=(TextView) findViewById(R.id.txtmovie);

        txtdata= (TextView) findViewById(R.id.txtdata);

        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 5000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, 0, period);





    }
    public void  AnimateandSlideShow() {

        System.out.print(slidingimage);
        Resources res = this.getResources();
        int resID = res.getIdentifier( moviedetails.get(currentimageindex%moviedetails.size()).getmovieimage(), "drawable", this.getPackageName());

        slidingimage.setImageResource(resID);
        textname.setText(moviedetails.get(currentimageindex%moviedetails.size()).getmoviename());
        txtdata.setMovementMethod(LinkMovementMethod.getInstance());
        txtdata.setText(Html.fromHtml("<a href='"+moviedetails.get(currentimageindex%moviedetails.size()).getmoviedata()+"'> Watch Trailer</a>"));

        currentimageindex++;

        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        txtdata.startAnimation(rotateimage);
        slidingimage.startAnimation(rotateimage);
        textname.startAnimation(rotateimage);


    }




}
