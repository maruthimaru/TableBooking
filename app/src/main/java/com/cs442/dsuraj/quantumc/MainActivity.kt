package com.cs442.dsuraj.quantumc

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import layout.GPlusFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var gpf: GPlusFragment? = null
    private var viewPager: ViewPager? = null
    private var drawer: DrawerLayout? = null
    private var tabLayout: TabLayout? = null
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
        }*/viewPager = findViewById<View>(R.id.view_pager) as ViewPager
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        drawer = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        setSupportActionBar(toolbar)
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