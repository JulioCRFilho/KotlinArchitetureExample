package com.example.uds.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.uds.R
import com.example.uds.databinding.ActivityHomeBinding
import com.example.uds.utils.BaseActivity
import com.example.uds.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.include_toolbar.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
//        val drawerBinding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.nav_header)
//        val menuBinding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.menu.nav_menu)
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        with(viewModel) {
            binding.viewModel = this
//            drawerBinding.viewModel = this
//            menuBinding.viewModel = this
        }

        setToolbar(toolbar, getString(R.string.home), false)
    }
}
