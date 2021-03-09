package com.valid.edson.ui.viewModels

import com.valid.edson.ui.models.HeaderModel
import com.valid.edson.ui.viewModels.base.BaseViewModel
import com.valid.utils.ViewManager

/**
 * Class used to manage the view model for the header view
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class HeaderViewModel : BaseViewModel() {
    var headerModel = HeaderModel()

    /**
     * Function to execute back in header button
     * */
    fun onBackHeader(){
        ViewManager.getInstance.onBack()
    }

    /**
     * Method to set the values in header
     * */
    fun setHeaderValues(headerTitle: String, isBackVisibility: Boolean) {
        headerModel.headerTitle = headerTitle
        headerModel.isBackVisibility = isBackVisibility
    }
}