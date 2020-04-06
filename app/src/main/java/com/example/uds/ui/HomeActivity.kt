package com.example.uds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.uds.R
import com.example.uds.databinding.ActivityHomeBinding
import com.example.uds.utils.setToolbar
import com.example.uds.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.include_toolbar.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        setToolbar(toolbar, getString(R.string.home), false)
    }
}
