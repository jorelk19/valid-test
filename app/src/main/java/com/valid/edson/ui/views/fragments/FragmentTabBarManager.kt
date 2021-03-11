package com.valid.edson.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.valid.edson.R
import com.valid.utils.ViewManager

class FragmentTabBarManager {

    companion object {
        private lateinit var currentInstance: FragmentTabBarManager
        val instance: FragmentTabBarManager
            get() {
                if (!::currentInstance.isInitialized) {
                    currentInstance = FragmentTabBarManager()
                }
                return currentInstance
            }
    }

    private val countryFragment: CountryFragment = CountryFragment()
    private val trackFragment: TrackFragment = TrackFragment()
    private var activeFragment: Fragment = countryFragment
    fun getCountryFragment(): CountryFragment = countryFragment
    fun getTrackFragment(): TrackFragment = trackFragment

    init {
        initControls()
    }

    private fun initControls() {
        ViewManager.getInstance.getCurrentActivity().supportFragmentManager.beginTransaction().add(R.id.main_container_layout, countryFragment).commit()
        ViewManager.getInstance.getCurrentActivity().supportFragmentManager.beginTransaction().add(R.id.main_container_layout, trackFragment).hide(trackFragment).commit()
    }

    fun <T> setCurrentTab(classTo: Class<T>, bundle: Bundle? = null) {
        when (classTo.simpleName) {
            CountryFragment::class.simpleName -> {
                countryFragment.arguments = bundle
                ViewManager.getInstance.getCurrentActivity().supportFragmentManager.beginTransaction().hide(activeFragment).show(countryFragment).commit()
                activeFragment = countryFragment
            }
            TrackFragment::class.simpleName -> {
                trackFragment.arguments = bundle
                ViewManager.getInstance.getCurrentActivity().supportFragmentManager.beginTransaction().hide(activeFragment).show(trackFragment).commit()
                activeFragment = trackFragment
            }
        }
    }
}