package com.example.uds.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.uds.R
import com.example.uds.api.AuthInterface
import com.example.uds.databinding.ActivityLoginBinding
import com.example.uds.utils.CustomDialog
import com.example.uds.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity(), AuthInterface {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.viewUI = this
        viewModel.authInterface = this

        verifyUser()
    }

    private fun verifyUser() {
        CustomDialog(this, viewModel.authLiveData, this)
        val user = viewModel.verifyUserLoggedIn()
        user?.let {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStarted() {
        CustomDialog(this, viewModel.authLiveData, this).show()
    }

    override fun onSuccess() {
        viewModel.authLiveData.value = Pair(1, null)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onFailure(message: String?) {
        viewModel.authLiveData.value = Pair(2, message)
    }

    fun signupNewUser(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}
