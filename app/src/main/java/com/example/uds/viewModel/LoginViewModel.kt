package com.example.uds.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.uds.api.AuthInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    var authInterface: AuthInterface? = null
    var email: String? = null
    var password: String? = null

    fun onLoginButtonClick(view: View) {
        authInterface?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authInterface?.onFailure("Email e/ou senha invalido")

        } else {
            firebaseAuth.signInWithEmailAndPassword(email ?: "", password ?: "")
                .addOnCompleteListener { auth ->
                    if (auth.isSuccessful) {
                        authInterface?.onSuccess()
                    } else {
                        auth.exception?.message?.let { msg ->
                            authInterface?.onFailure(msg)
                        }
                    }
                }
        }
    }

    fun verifyUserLoggedIn(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}