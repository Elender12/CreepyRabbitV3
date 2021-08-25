package com.ecirstea.creepyrabbit

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ecirstea.creepyrabbit.databinding.ActivityMainBinding
import com.ecirstea.creepyrabbit.ui.navigation.HomeActivity
import com.ecirstea.creepyrabbit.ui.view.RegisterActivity
import com.ecirstea.creepyrabbit.ui.viewmodel.JwtViewModel
import com.ecirstea.creepyrabbit.utils.SharedApp

private const val TAG = "TAG"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val jwtViewModel: JwtViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        jwtViewModel.jwtModel.observe(this, {
            if(it?.token != null){
                Log.d(TAG, "onCreate: username: ${it.username}")
                SharedApp.prefs.name = it.username
                SharedApp.prefs.token= it.token
                val intent = Intent(this, HomeActivity::class.java).apply {
                    //putExtra(EXTRA_MESSAGE, message)
                }
                Log.d(TAG, "onCreate main activity: before starting activity")
                startActivity(intent)
            }else{
                Toast.makeText(this, "Nope, failed in Auth", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonLogin.setOnClickListener { login() }
        binding.textViewRegisterNow.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        jwtViewModel.login(email, password)
    }
}