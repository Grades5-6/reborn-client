package com.example.client

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class OnBoardingAdapter(fm:FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount()=5

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> OnBoarding1stFragment()
            1 -> OnBoarding2ndFragment()
            2 -> OnBoarding3rdFragment()
            3 -> OnBoarding4thFragment()
            else -> OnBoarding5thFragment()
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}