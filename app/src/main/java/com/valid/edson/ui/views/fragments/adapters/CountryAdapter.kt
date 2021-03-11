package com.valid.edson.ui.views.fragments.adapters

import android.annotation.SuppressLint
import android.content.Context
import com.valid.businessmodels.business.Country
import com.valid.edson.R
import com.valid.edson.databinding.LayoutCountryItemBinding
import com.valid.edson.ui.views.fragments.FragmentTabBarManager
import com.valid.edson.ui.views.fragments.TrackFragment
import com.valid.edson.utils.CountryFlagMapper
import com.valid.edson.utils.loadLocalImage
import com.valid.edson.utils.settingsSharedPreferences
import com.valid.utils.GenericAdapter
import com.valid.utils.ViewManager


/**
 * Class used to manage the adapter to load sites in recycler view
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class CountryAdapter(private val context: Context, arrayList: ArrayList<Country>) : GenericAdapter<Country, LayoutCountryItemBinding>(context, arrayList) {
    override fun getLayoutResId(): Int {
        return R.layout.layout_country_item
    }

    /**
     * Method to set the data in the recycler view layout
     * */
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindData(model: Country, position: Int, dataBinding: LayoutCountryItemBinding) {
        dataBinding.tvCountryName.text = model.countryName
        dataBinding.ivCountryFlag.loadLocalImage(CountryFlagMapper.getImageCountry(model.countryCode))
        setListeners(dataBinding, model)
    }

    /**
     * Method used to set the listeners for container and launch search fragment
     * */
    private fun setListeners(dataBinding: LayoutCountryItemBinding, model: Country) {
        dataBinding.siteContainer.setOnClickListener {
            settingsSharedPreferences.setCountry(model)
            FragmentTabBarManager.instance.setCurrentTab(TrackFragment::class.java)
        }
    }
}