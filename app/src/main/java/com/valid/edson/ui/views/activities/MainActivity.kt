package com.valid.edson.ui.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.valid.businessmodels.business.Country
import com.valid.edson.R
import com.valid.edson.databinding.ActivityMainBinding
import com.valid.edson.ui.viewModels.MainViewModel
import com.valid.edson.ui.views.activities.base.BaseFragmentActivity
import com.valid.edson.utils.getViewModelFactory
import com.valid.utils.fromJson


class MainActivity : BaseFragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var currentCountries: ArrayList<Country>
    private val viewModel by viewModels<MainViewModel> { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        getExtras()
        viewModel.showCountryFragmentClick()
    }

    private fun getExtras() {
        intent.extras?.let {
            currentCountries = it.getString(CURRENT_COUNTRIES)?.fromJson() ?: arrayListOf()
            viewModel.setCurrentCountries(currentCountries)
        }
    }

    companion object {
        const val CURRENT_COUNTRIES = "CURRENT_COUNTRIES"
    }
}