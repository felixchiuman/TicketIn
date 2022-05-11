package com.felix.ticketin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.felix.ticketin.R
import com.felix.ticketin.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
//    private val mainMenuFragment = MainMenuFragment()
//    private val comingSoonFragment = ComingSoonFragment()
//    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNav
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.login_Activity){
//                navController.popBackStack()
//            }
//        }

//        replaceFragment(mainMenuFragment)
//        replaceFragment(comingSoonFragment)

//        binding.bottomNav.setOnNavigationItemSelectedListener {
//            when (it.itemId){
//                R.id.home -> replaceFragment(mainMenuFragment)
//                R.id.ticket -> replaceFragment(comingSoonFragment)
//              R.id.logout -> logout()
//            }
//            true
//        }

        //init firebase
//        firebaseAuth = FirebaseAuth.getInstance()
//        checkUser()
    }

//    private  fun replaceFragment(fragment: Fragment){
//        if(fragment != null){
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, fragment)
//            transaction.commit()
//        }
//    }

//    private fun logout(){
//        startActivity(Intent(this, LoginActivity::class.java))
//        finish()
//    }

//    private fun checkUser() {
//        //check user telah login atau belum
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser != null){
//            //get user info
//            val email = firebaseUser.email
//            binding.tvUser.text = "Hello, \n${email}"
//        }
//        else{
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
//    }

}