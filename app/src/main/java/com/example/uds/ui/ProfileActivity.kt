package com.example.uds.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.uds.R
import com.example.uds.databinding.ActivityProfileBinding
import com.example.uds.utils.BaseActivity
import com.example.uds.viewModel.ProfileViewModel
import kotlinx.android.synthetic.main.include_toolbar.*

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.viewModel = viewModel

        setToolbar(toolbar, "Perfil", userName = viewModel.userName ?: "")
    }
}
