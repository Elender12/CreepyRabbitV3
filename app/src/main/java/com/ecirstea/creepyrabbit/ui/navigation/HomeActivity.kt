package com.ecirstea.creepyrabbit.ui.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ecirstea.creepyrabbit.MainActivity
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.utils.SharedApp
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.nav_header.*
private const val TAG= "HomeActivity"
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
                if(destination.label?.equals("SignOut") == true){
                    this.closeOptionsMenu()
                    //SharedApp.prefs.token =""
                    //SharedApp.prefs.name= ""
                    val intent = Intent(this, MainActivity::class.java).apply {
                        //putExtra(EXTRA_MESSAGE, message)
                    }
                    startActivity(intent)
                }
                textTitle.text = destination.label
            }

        //Shared preferences
   /*     val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREF_FILE,
            Context.MODE_PRIVATE)
        val prefUsername = sharedPreferences.getString("name_key","Anonymous")*/

        val headerView : View = navView.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.username)
        navUsername.text =    SharedApp.prefs.name
    }
}