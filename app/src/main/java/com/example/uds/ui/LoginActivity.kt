package com.example.uds.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.uds.R
import com.example.uds.api.AuthInterface
import com.example.uds.databinding.ActivityLoginBinding
import com.example.uds.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity(), AuthInterface {
    private lateinit var viewModel: LoginViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authInterface = this

        verifyUser()
    }

    private fun verifyUser() {
        val user = viewModel.verifyUserLoggedIn()
        user?.let {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStarted() {
        Toast.makeText(this, "logando", Toast.LENGTH_LONG).show()
    }

    override fun onSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
