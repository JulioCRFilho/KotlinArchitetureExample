package com.example.uds.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.uds.R
import com.example.uds.adapter.TabsAdapter
import com.example.uds.utils.BaseActivity
import com.example.uds.utils.NewScheduleDialog
import com.example.uds.viewModel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_toolbar.toolbar

class HomeActivity : BaseActivity() {
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setToolbar(toolbar, getString(R.string.home), false, viewModel.userName)

        viewPager.adapter = TabsAdapter(supportFragmentManager, viewModel)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setTabTextColors(getColor(R.color.colorPrimary), getColor(R.color.colorPrimary))

        floatBtn.setOnClickListener {
            NewScheduleDialog(this, viewModel).show()
        }
    }
}
