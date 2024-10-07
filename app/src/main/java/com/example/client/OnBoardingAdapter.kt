package com.example.client

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingAdapter(onBoardingActivity: OnBoardingActivity) : FragmentStateAdapter(onBoardingActivity) {

    private val fragments = listOf(
        OnBoarding1stFragment(),
        OnBoarding2ndFragment(),
        OnBoarding3rdFragment(),
        OnBoarding4thFragment(),
        OnBoarding5thFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]


}