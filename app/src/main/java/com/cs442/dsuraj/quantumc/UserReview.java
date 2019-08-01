package com.cs442.dsuraj.quantumc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sushma on 11/24/2016.
 */

public class UserReview extends AppCompatActivity {
    ArrayList<TableReview> moviereview;
    ListView movielist;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        //create Array list of menu items
        moviereview = new ArrayList();

        moviereview.add(new TableReview("Avenger", "@drawable/avengerreview",5,"Excellent"));

        moviereview.add(new TableReview("Flypaper", "@drawable/flypaperreview",3,"Good"));

        moviereview.add(new TableReview("Mission Impossible", "@drawable/missionimpossible",2,"Not Good"));

        moviereview.add(new TableReview("Spiderman", "@drawable/spidermanreview",1,"Worst Movie Ever"));
        adapter=new CustomAdapter(this, moviereview);

        movielist=(ListView)findViewById(R.id.myListView);
        movielist.setAdapter(adapter);

    }


}
