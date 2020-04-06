package com.example.uds.utils

import android.content.Intent
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.uds.R
import com.example.uds.ui.LoginActivity
import com.example.uds.ui.ProfileActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.nav_header.view.*

open class BaseActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val firebaseAuth = FirebaseAuth.getInstance()

    protected fun setToolbar(toolbar: Toolbar, title: String, homeEnabled: Boolean = true, userName: String = "") {
        toolbar.title = title
        this.setSupportActionBar(toolbar)
        if (homeEnabled) {
            supportActionBar?.setDisplayHomeAsUpEnabled(homeEnabled)
        } else {
            val toogle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
            drawer.addDrawerListener(toogle)
            toogle.syncState()
            nav_view.setNavigationItemSelectedListener(this)

            val headerView = nav_view.getHeaderView(0)
            val userNameTextView: TextView = headerView.findViewById(R.id.userNameTextView)
            userNameTextView.text = userName
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_logout -> {
                firebaseAuth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}