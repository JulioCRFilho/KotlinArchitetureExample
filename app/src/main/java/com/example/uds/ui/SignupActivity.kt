package com.example.uds.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.uds.R
import com.example.uds.api.AuthInterface
import com.example.uds.databinding.ActivitySignupBinding
import com.example.uds.utils.CustomDialog
import com.example.uds.utils.setToolbar
import com.example.uds.viewModel.SignupViewModel
import kotlinx.android.synthetic.main.include_toolbar.*

class SignupActivity : AppCompatActivity(), AuthInterface {
    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authInterface = this

        setToolbar(toolbar, getString(R.string.signup))
    }

    override fun onStarted() {
        d("teste", "onStarted")
        CustomDialog(this, viewModel.signupLiveData, this).show()
    }

    override fun onSuccess() {
        viewModel.signupLiveData.value = Pair(1, null)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String?) {
        viewModel.signupLiveData.value = Pair(2, message)
    }
}
