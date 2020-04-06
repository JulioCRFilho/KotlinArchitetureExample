package com.example.uds.ui

import android.os.Bundle
import android.util.Log.d
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.uds.R
import com.example.uds.adapter.TabsAdapter
import com.example.uds.databinding.ActivityHomeBinding
import com.example.uds.utils.BaseActivity
import com.example.uds.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_toolbar.toolbar


class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.viewModel = viewModel

        setToolbar(toolbar, getString(R.string.home), false, viewModel.userName)

        viewPager?.adapter = TabsAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
