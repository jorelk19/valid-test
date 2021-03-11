package com.valid.edson.utils

import com.valid.edson.R

/**
 * Object used to map the different site identification and get the correct drawable
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
object CountryFlagMapper {
    fun getImageCountry(id: String): Int {
        return when (id) {
            "CO" -> {
                R.drawable.colombia
            }
            "SP" -> {
                R.drawable.espana
            }
            "MX" -> {
                R.drawable.mexico
            }
            "UK" -> {
                R.drawable.united_kingdom
            }
            "US" -> {
                R.drawable.united_states
            }
            "AR" -> {
                R.drawable.argentina
            }
            else -> {
                R.drawable.colombia
            }
        }
    }
}