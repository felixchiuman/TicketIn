package com.felix.ticketin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.felix.ticketin.R
import com.felix.ticketin.databinding.ActivityMainBinding
import com.felix.ticketin.firebase.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
    }

    private fun checkUser() {
        //check user telah login atau belum
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            //get user info
            val email = firebaseUser.email
            binding.tvUser.text = "Hello, \n${email}"
        }
        else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}