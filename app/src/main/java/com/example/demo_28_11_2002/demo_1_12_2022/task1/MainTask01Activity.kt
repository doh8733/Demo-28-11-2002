package com.example.demo_28_11_2002.demo_1_12_2022.task1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase.NotifiCationActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main_task01.*
import kotlinx.android.synthetic.main.activity_main_task01.view.*
import kotlinx.android.synthetic.main.layout_header_nav.view.*

class MainTask01Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private const val FRAGMENT_SEARCH = 0
        private const val FRAGMENT_HEART = 1
    }

    private var mCurrenFragment = FRAGMENT_SEARCH

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task01)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setSupportActionBar(toolbar)
        toolbar.title = "Demo toolbar"
//        btnToast.setOnClickListener {
//            Toast.makeText(this, "Hello word", Toast.LENGTH_SHORT).show()
//        }
//        btnSnakeBar.setOnClickListener {
//            Snackbar.make(findViewById(R.id.layoutContainerTask1),"Hi this is snackbar",Snackbar.LENGTH_SHORT).show()
//        }
        val actionBar = supportActionBar

        val toggle = ActionBarDrawerToggle(
            this,
            layoutContainerTask1,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        layoutContainerTask1.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        replaceFragment(SearchkFragment())

        val name = intent.extras?.getString("name")
        val email = intent.extras?.getString("email")
        val naview = navView.inflateHeaderView(R.layout.layout_header_nav)
        naview.name.text = name
        naview.email.text = email
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.actionSearch){
            Toast.makeText(this, "${item.title}", Toast.LENGTH_SHORT).show()
        }
        if(item.itemId == R.id.actionHeartBroken){
            Snackbar.make(findViewById(R.id.layoutContainerTask1),"Hi this is snackbar",Snackbar.LENGTH_SHORT).show()
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        when (id) {
            R.id.actionSearchMenu -> {
                if (mCurrenFragment != FRAGMENT_SEARCH) {
                    replaceFragment(SearchkFragment())
                    mCurrenFragment = FRAGMENT_SEARCH
                }
            }
            R.id.actionHeartMenu -> {
                if (mCurrenFragment != FRAGMENT_HEART) {
                    replaceFragment(HeartFragment())
                    mCurrenFragment = FRAGMENT_HEART
                }
            }
            R.id.pushNotification ->{
                startActivity(Intent(this,NotifiCationActivity::class.java))
            }
        }
        layoutContainerTask1.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (layoutContainerTask1.isDrawerOpen(GravityCompat.START)) {
            layoutContainerTask1.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containFrame, fragment)
        transaction.commit()
    }
}