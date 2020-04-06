package com.example.uds.utils

import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.uds.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*

open class BaseActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    protected fun setToolbar(toolbar: Toolbar, title: String, homeEnabled: Boolean = true) {
        toolbar.title = title
        this.setSupportActionBar(toolbar)
        if (homeEnabled) {
            supportActionBar?.setDisplayHomeAsUpEnabled(homeEnabled)
        } else {
            val toogle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
            drawer.addDrawerListener(toogle)
            toogle.syncState()
            nav_view.setNavigationItemSelectedListener(this)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "PROFILE", Toast.LENGTH_LONG).show()
            }

            R.id.nav_logout -> {
                Toast.makeText(this, "PROFILE", Toast.LENGTH_LONG).show()
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}