package com.ecirstea.creepyrabbit.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ecirstea.creepyrabbit.R
import com.google.android.material.navigation.NavigationView

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val imgMenu = findViewById<ImageView>(R.id.imgMenu)
        val navView = findViewById<NavigationView>(R.id.navDrawer)
        navView.itemIconTintList= null
        imgMenu.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(navView,navController)
        val textTitle = findViewById<TextView>(R.id.title)

        navController
            .addOnDestinationChangedListener { _, destination, _ ->
                textTitle.text = destination.label
            }

    }
}