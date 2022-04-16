package com.felix.ticketin.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.felix.ticketin.R
import com.felix.ticketin.databinding.ActivityLoginBinding
import com.felix.ticketin.ui.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init firebase
        firebaseAuth = FirebaseAuth.getInstance()
//        checkUser()

        binding.btnLogin.setOnClickListener {
            //sebelum login di validasi dlu datanya
            validateData()
        }

        binding.tvSignUp.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.tvForget.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        email = binding.etLoginEmail.editText?.text.toString().trim()
        password = binding.etLoginPassword.editText?.text.toString().trim()

        //validasi data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.etLoginEmail.error = "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            //pw blank
            binding.etLoginPassword.error = "Please enter your password"
        }
        else{
            firebaseResetPass()
        }

    }

    private fun firebaseResetPass() {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Email Sent !!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateData() {
        //get data
        email = binding.etLoginEmail.editText?.text.toString().trim()
        password = binding.etLoginPassword.editText?.text.toString().trim()

        //validasi data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.etLoginEmail.error = "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            //pw blank
            binding.etLoginPassword.error = "Please enter your password"
        }
        else{
            //data valid
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }
            .addOnFailureListener {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
    }

//    private fun checkUser(){
//        //if user telah login akan ke main activity
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser != null){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//    }
}