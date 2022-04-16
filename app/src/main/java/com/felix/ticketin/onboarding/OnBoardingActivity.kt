package com.felix.ticketin.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.felix.ticketin.R
import com.felix.ticketin.firebase.LoginActivity
import com.felix.ticketin.model.OnBoardingData
import com.google.android.material.tabs.TabLayout

class OnBoardingActivity : AppCompatActivity() {

    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager : ViewPager? = null
    var next: TextView? = null
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.tv_next)

        val onBoardingData:MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Now Playing","Watch and find movies that bring your mood back",R.drawable.play_button))
        onBoardingData.add(OnBoardingData("Pre-Order","Don't worry about missing the latest movie update", R.drawable.calender))
        onBoardingData.add(OnBoardingData("Cashless","You can use e-wallet for the transaction", R.drawable.phone))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        next?.setOnClickListener {
            if (position < onBoardingData.size){
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if (position == onBoardingData.size){
                val loginActivity = Intent(this, LoginActivity::class.java)
                startActivity(loginActivity)
            }
        }
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onBoardingViewPager = findViewById(R.id.vp_onboarding)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }
}