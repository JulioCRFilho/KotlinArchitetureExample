package com.example.uds.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.uds.R
import com.example.uds.adapter.TabsAdapter
import com.example.uds.databinding.ActivityHomeBinding
import com.example.uds.utils.BaseActivity
import com.example.uds.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_toolbar.toolbar

class HomeActivity : BaseActivity() {
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setToolbar(toolbar, getString(R.string.home), false, viewModel.userName)

        binding.viewModel = viewModel
        viewPager.adapter = TabsAdapter(supportFragmentManager, viewModel, this)
        viewPager.currentItem = 0
        tabLayout.setupWithViewPager(viewPager)
    }
}
