package com.valid.edson.ui.views.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.valid.edson.R
import com.valid.edson.databinding.ActivityMainBinding
import com.valid.edson.ui.views.activities.base.BaseFragmentActivity


class MainActivity : BaseFragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.headerToolbar)
        setSupportActionBar(toolbar)
        setNavigationConfiguration()
        initControls()
    }

    private fun setNavigationConfiguration() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.paymentFragment, R.id.myPaymentsFragment
            ), binding.drawerLayout
        )
        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun initControls() {
        //ViewManager.getInstance.attachFragment(MoviesFragment(), R.id.fragment_container)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /*fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id: Int = item.getItemId()
        if (id == R.id.nav_menu1) {
            // Handle the camera action
        } else if (id == R.id.nav_menu2) {
        } else if (id == R.id.nav_menu3) {
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }*/
}