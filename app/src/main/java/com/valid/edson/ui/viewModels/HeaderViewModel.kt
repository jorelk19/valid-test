package com.valid.edson.ui.viewModels

import androidx.lifecycle.MutableLiveData
import com.valid.edson.ui.models.HeaderModel
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.utils.ViewManager

/**
 * Class used to manage the view model for the header view
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class HeaderViewModel : BaseViewModel() {

    val headerTitle = MutableLiveData<String>()
    val isBackVisibility = MutableLiveData<Boolean>()

    /**
     * Function to execute back in header button
     * */
    fun onBackHeader(){
        ViewManager.getInstance.onBack()
    }

    /**
     * Method to set the values in header
     * */
    fun setHeaderValues(title: String, backVisibility: Boolean) {
        headerTitle.value = title
        isBackVisibility.value = backVisibility
    }
}