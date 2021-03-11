package com.valid.edson.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.valid.businessmodels.business.Country
import com.valid.edson.R
import com.valid.edson.databinding.CountryFragmentBinding
import com.valid.edson.ui.viewModels.CountryViewModel
import com.valid.edson.ui.views.fragments.adapters.CountryAdapter
import com.valid.edson.utils.getViewModelFactory
import com.valid.utils.ViewManager
import com.valid.utils.fromJson

/**
 * Class used to manage the sites view
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class CountryFragment : Fragment() {
    private lateinit var siteFragmentBinding: CountryFragmentBinding
    private val viewModel by viewModels<CountryViewModel> { getViewModelFactory() }
    private var countryAdapter : CountryAdapter? = null
    private lateinit var currentCountries : ArrayList<Country>

    /**
     * Method that allow get the site fragment layout
     * */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        siteFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.country_fragment, container, false)
        getExtras()
        loadCountries()
        setAdapter()
        addSubscriptions()
        return siteFragmentBinding.root
    }

    private fun getExtras() {
        arguments?.let {
            val countries = it.getString(CURRENT_COUNTRIES)?.fromJson<ArrayList<Country>>()
            countries?.let {
                setCountries(countries)
            }
        }
    }

    private fun loadCountries() {
        viewModel.setCurrentCountries(countries = currentCountries)
    }

    /**
     * Method used to set adapter to show site information list
     * */
    private fun setAdapter() {
        countryAdapter = CountryAdapter(ViewManager.getInstance.getCurrentActivity(), arrayListOf())
        siteFragmentBinding.rvCountry.adapter = countryAdapter
        siteFragmentBinding.rvCountry.layoutManager = GridLayoutManager(ViewManager.getInstance.getCurrentActivity(), 2)
    }

    /**
     * Method to manage the subscription and observe the sites when information is loaded from the services
     * */
    private fun addSubscriptions() {
        viewModel.getCountries().observe(viewLifecycleOwner, Observer {
            countryAdapter?.addItems(it)
        })
    }

    private fun setCountries(countries: java.util.ArrayList<Country>) {
        currentCountries = countries
    }

    companion object {
        const val CURRENT_COUNTRIES = "CURRENT_COUNTRIES"
    }

    override fun onDestroy() {
        super.onDestroy()
        siteFragmentBinding.rvCountry.adapter = null
    }
}