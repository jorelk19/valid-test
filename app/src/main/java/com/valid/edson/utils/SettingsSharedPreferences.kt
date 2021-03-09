package com.valid.edson.utils

import android.content.Context
import android.content.SharedPreferences
import com.valid.businessmodels.business.User
import com.valid.di.app.App
import com.valid.utils.fromJson
import com.valid.utils.json

const val PREFERENCES_FILE_KEY = "com.mercadolibre.preferences"
const val CURRENT_USER = "CURRENT_USER"

/**
 * Class use to store setting shared preferences
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * **/
class SettingsSharedPreferences(appContext: Context) {

    private val sharedPreferences: SharedPreferences = appContext.getSharedPreferences(
        PREFERENCES_FILE_KEY, Context.MODE_PRIVATE
    )

    private fun int(key: String) = sharedPreferences.getInt(key, 0)
    private fun boolean(key: String) = sharedPreferences.getBoolean(key, false)
    private fun string(key: String) = sharedPreferences.getString(key, "") ?: ""
    private fun setInt(key: String, value: Int) = this.sharedPreferences.edit().putInt(key, value).commit()
    private fun setString(key: String, value: String) = this.sharedPreferences.edit().putString(key, value).commit()
    private fun setBoolean(key: String, value: Boolean) = this.sharedPreferences.edit().putBoolean(key, value).commit()

    fun setCurrentUser(user: User) {
        setString(CURRENT_USER, user.json())
    }

    fun getCurrentUser(): User {
        return string(CURRENT_USER).fromJson()
    }
}

val settingsSharedPreferences by lazy { SettingsSharedPreferences(App.getAppContext()) }
