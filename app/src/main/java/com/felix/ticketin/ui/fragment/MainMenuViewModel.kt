package com.felix.ticketin.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainMenuViewModel : ViewModel() {
    var email: MutableLiveData<String> = MutableLiveData("Hello, There")

    fun uncensored(){
        email.value  = "Have a\nNice Day :D"
        }
    }

//    private fun checkUser() {
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser != null){
//            //get user info
//            val email = firebaseUser.email
//        }
//    }
