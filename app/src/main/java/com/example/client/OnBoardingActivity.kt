package com.example.client

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.client.databinding.ActivityOnBoardingBinding
import com.example.client.onboarding.OnBoardingAdapter

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var adapter : OnBoardingAdapter

    object OnboardingPreferences {
        private const val PREFS_NAME = "onboarding_prefs"
        private const val KEY = "has_seen_onboarding"

        fun hasSeenOnboarding(context: Context):Boolean{
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(KEY,false)
        }

        fun setHasSeenOnboarding(context: Context){
            val sharedPreferences=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
            with(sharedPreferences.edit()){
                putBoolean(KEY, true)
                apply()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= OnBoardingAdapter(this)
        binding.viewPagerMain.adapter=adapter

        binding.viewPagerMain.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPagerMain.layoutDirection=ViewPager2.LAYOUT_DIRECTION_LTR

        binding.dotIndicator.setViewPager2(binding.viewPagerMain)

        binding.viewPagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

    }

}