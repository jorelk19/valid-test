package com.valid.edson.ui.views.activities.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.valid.edson.ui.views.activities.MainActivity
import com.valid.utils.ViewManager

/**
 * Class used to manage the activities in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
open class BaseFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ViewManager.getInstance.setCurrentActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewManager.getInstance.setCurrentActivity(this)
    }

    override fun onResume() {
        super.onResume()
        ViewManager.getInstance.setCurrentActivity(this)
    }

    override fun onBackPressed() {
        if (ViewManager.getInstance.getCurrentActivity()::class.java != MainActivity::class.java) {
            ViewManager.getInstance.onBack()
        }
    }

}