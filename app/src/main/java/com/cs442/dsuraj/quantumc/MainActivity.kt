package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.cs442.dsuraj.quantumc.db.AppDatabase
import com.cs442.dsuraj.quantumc.db.dao.MovieDao
import com.cs442.dsuraj.quantumc.db.table.Movies
import com.cs442.dsuraj.quantumc.retrofit.CustomDialog
import com.cs442.dsuraj.quantumc.retrofit.Utils
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.google.gson.Gson
import com.scoto.visitormanagent.retrofit.ApiService
import com.scoto.visitormanagent.retrofit.RetrofitClientInstance
import layout.GPlusFragment
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var gpf: GPlusFragment? = null
    private var viewPager: ViewPager? = null
    private var drawer: DrawerLayout? = null
    private var tabLayout: TabLayout? = null
    lateinit var customDialog: CustomDialog
    lateinit var appDatabase: AppDatabase
    val TAG=MainActivity::class.java.simpleName
    lateinit var movieDao: MovieDao
    private val pageTitle = arrayOf("Round Table", "Meeting Table", "Premium Table", "Celebrity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        ActionBar actionbar = getActionBar();

        TextView textview = new TextView(MainActivity.this);

        RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        textview.setLayoutParams(layoutparams);

        textview.setText("Action Bar Title Text");

        textview.setGravity(Gravity.CENTER);

        textview.setTextColor(Color.parseColor("#FFFFFF"));

        textview.setTextSize(25);


        if (actionbar != null) {
            actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionbar.setCustomView(textview);
        }*/
        customDialog= CustomDialog(this)
        viewPager = findViewById<View>(R.id.view_pager) as ViewPager
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        drawer = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        setSupportActionBar(toolbar)
        appDatabase= AppDatabase.getDatabase(this)
        movieDao=appDatabase.movies()
        val movieList=ArrayList<Movies>()
        movieDao.deleteAll()
        movieList.add(Movies(1,"Round Table","A man caught in the middle of two simultaneous robberies at the same bank desperately tries to protect the teller with whom he secretly in love.","5"))
        movieList.add(Movies(2,"Meeting Table","Ethan and team take on their most impossible mission yet, eradicating the Syndicate - an International rogue organization as highly skilled as they are, committed to destroying the IMF.","4"))
        movieList.add(Movies(3,"Premium Table","Earths mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity.","3"))
        movieList.add(Movies(4,"Celebrity Table","When New York is put under siege by Oscorp, it is up to Spider-Man to save the city he swore to protect as well as his loved ones.","4"))
        movieDao.insert(movieList)
        Log.e(TAG,"json object " + Gson().toJson(movieList))
        getInserTable(movieList)
        //create default navigation drawer toggle
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()
        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = findViewById<View>(R.id.tab_layout) as TabLayout
        for (i in 0..3) {
            tabLayout!!.addTab(tabLayout!!.newTab().setText(pageTitle[i]))
        }
        //set gravity for tab bar
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        //handling navigation view item event
        val navigationView = (findViewById<View>(R.id.nav_view) as NavigationView)
        navigationView.setNavigationItemSelectedListener(this)
        //set viewpager adapter
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = pagerAdapter
        //change Tab selection when swipe ViewPager
        viewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
        //change ViewPager page when tab selected
        tabLayout!!.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


    private fun getInserTable(movieList:ArrayList<Movies>) {
//        alertDialog = customDialog.loading(activity!!)
        val showme=customDialog.processDialog()
        val service = RetrofitClientInstance.createServices(ApiService::class.java, Utils.baseUrl)
        val listCall = service.inserTablet(movieList)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<ArrayList<Movies>> {
            override fun onFailure(call: Call<ArrayList<Movies>>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                showme.dismiss()
            }
            override fun onResponse(call: Call<ArrayList<Movies>>, response: retrofit2.Response<ArrayList<Movies>>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.body() != null) {
                    showme.dismiss()
                }else{
                    showme.dismiss()
                }
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.fr1) {
            viewPager!!.currentItem = 0
        } else if (id == R.id.fr2) {
            viewPager!!.currentItem = 1
        } else if (id == R.id.fr3) {
            viewPager!!.currentItem = 2
        } else if (id == R.id.fr4) {
            viewPager!!.currentItem = 4
        } else if (id == R.id.comingsoon) {
            val intent = Intent(this, ComingSoon::class.java)
            startActivity(intent)
        } else if (id == R.id.Login) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        } else if (id == R.id.review) {
            val intent = Intent(this, UserReview::class.java)
            startActivity(intent)
        } else if (id == R.id.close) {
            finish()
        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        assert(drawer != null)
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}