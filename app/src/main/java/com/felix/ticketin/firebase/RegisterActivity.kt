package com.felix.ticketin.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.felix.ticketin.R
import com.felix.ticketin.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            //validate data
            validateData()
        }
    }

    private fun validateData() {
        email = binding.etRegisterEmail.editText?.text.toString().trim()
        password = binding.etRegisterPassword.editText?.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //cek fromat email
            binding.etRegisterEmail.error = "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            //password kosong
            binding.etRegisterPassword.error = "Please enter your password"
        }
        else if (password.length < 6){
            binding.etRegisterPassword.error = "Password must atleast 6 character long"
        }
        else{
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, RegisterSuccessActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show()
            }
    }
}