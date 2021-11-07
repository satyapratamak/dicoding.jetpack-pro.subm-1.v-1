package com.satya.subm.submission

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.satya.subm.submission.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var drawer_layout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navController:NavController

    private lateinit var nav_view: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer_layout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitleTextColor(Color.WHITE);
        nav_view = findViewById(R.id.nav_view)

        setSupportActionBar(findViewById(R.id.toolbar))

        navController =  Navigation.findNavController(this, R.id.fragment_container)
        NavigationUI.setupWithNavController(nav_view, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        val toogle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_open,
            R.string.navigation_close
        )
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()

        /*val binding  = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_view)*/

        /*drawer_layout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        nav_view = findViewById(R.id.nav_view)

        setSupportActionBar(findViewById(R.id.toolbar))*/


    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}