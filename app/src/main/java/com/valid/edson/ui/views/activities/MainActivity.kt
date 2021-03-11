package com.valid.edson.ui.views.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.AppBarConfiguration
import com.valid.businessmodels.business.Country
import com.valid.edson.R
import com.valid.edson.databinding.ActivityMainBinding
import com.valid.edson.ui.views.activities.base.BaseFragmentActivity
import com.valid.edson.ui.views.fragments.CountryFragment
import com.valid.edson.ui.views.fragments.FragmentTabBarManager
import com.valid.edson.ui.views.fragments.TrackFragment
import com.valid.utils.fromJson
import com.valid.utils.json


class MainActivity : BaseFragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var currentCountries: ArrayList<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        getExtras()
        setBottomNavigationListener()
        setSelectedBottomNavigationItem()
        goToCountryFragment()
    }

    private fun setBottomNavigationListener() {
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_country -> {
                    goToCountryFragment()
                    setItemCheckedAtPosition(0)
                    !it.isChecked
                }
                R.id.action_track -> {
                    FragmentTabBarManager.instance.setCurrentTab(TrackFragment::class.java)
                    setItemCheckedAtPosition(1)
                    !it.isChecked
                }
                else -> false
            }
        }
    }

    private fun goToCountryFragment() {
        val bundle = Bundle().apply {
            putString(CountryFragment.CURRENT_COUNTRIES, currentCountries.json())
        }
        FragmentTabBarManager.instance.setCurrentTab(CountryFragment::class.java, bundle)
    }

    private fun setSelectedBottomNavigationItem() {
        setItemCheckedAtPosition(0)
    }

    private fun setItemCheckedAtPosition(position: Int) {
        binding.mainBottomNavigation.menu.getItem(position).isChecked = true
    }

    private fun getExtras() {
        intent.extras?.let {
            currentCountries = it.getString(CURRENT_COUNTRIES)?.fromJson() ?: arrayListOf()
        }
    }

    companion object {
        const val CURRENT_COUNTRIES = "CURRENT_COUNTRIES"
    }
}