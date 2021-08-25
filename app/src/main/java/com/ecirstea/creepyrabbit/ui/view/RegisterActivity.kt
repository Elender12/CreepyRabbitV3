package com.ecirstea.creepyrabbit.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.ecirstea.creepyrabbit.MainActivity
import com.ecirstea.creepyrabbit.data.model.user.User
import com.ecirstea.creepyrabbit.databinding.ActivityRegisterBinding
import com.ecirstea.creepyrabbit.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

private const val TAG= "register"
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
         userViewModel.userModel.observe(this, {
             if(it != null){
                 Toast.makeText(this, "User registered successfully!", Toast.LENGTH_LONG).show()
                 val intent = Intent(this, MainActivity::class.java).apply {
                 }
                 startActivity(intent)
             }else{
                 Toast.makeText(this, "There was en error during the registration", Toast.LENGTH_LONG).show()
             }
         })
        buttonRegister.setOnClickListener{
            register()
        }
    }

    private fun register() {
        val name = binding.ETRegisteredName.text.toString().trim()
        val username = binding.ETRegisteredUsername.text.toString().trim()
        val email = binding.ETRegisteredEmail.text.toString().trim()
        val password = binding.ETRegisteredPassword.text.toString().trim()
        //TODO validations for data
        userViewModel.saveUser(User(name=name, username = username, password = password, email = email))
    }
}