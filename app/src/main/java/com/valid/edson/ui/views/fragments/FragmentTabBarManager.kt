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

    private lateinit var countryFragment: CountryFragment
    private lateinit var artistFragment: ArtistFragment
    private lateinit var activeFragment: Fragment
    fun getCountryFragment(): CountryFragment = countryFragment
    fun getTrackFragment(): ArtistFragment = artistFragment

    fun <T> setCurrentTab(classTo: Class<T>, bundle: Bundle? = null) {
        when (classTo.simpleName) {
            CountryFragment::class.simpleName -> {
                countryFragment = CountryFragment()
                countryFragment.arguments = bundle
                ViewManager.getInstance.attachFragment(countryFragment, R.id.main_container_layout, addNewTransaction = false)
                if(::activeFragment.isInitialized){
                    activeFragment.onDestroy()
                }
                activeFragment = countryFragment
            }
            ArtistFragment::class.simpleName -> {
                artistFragment = ArtistFragment()
                artistFragment.arguments = bundle
                ViewManager.getInstance.attachFragment(artistFragment, R.id.main_container_layout, addNewTransaction = false)
                if(::activeFragment.isInitialized){
                    activeFragment.onDestroy()
                }
                activeFragment = artistFragment
            }
        }
    }
}