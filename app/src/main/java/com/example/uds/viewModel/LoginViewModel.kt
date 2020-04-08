package com.example.uds.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uds.api.AuthInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    var email: String? = null
    var password: String? = null

    var authInterface: AuthInterface? = null
    val authLiveData = MutableLiveData<Pair<Int?, String?>>()

    fun onLoginButtonClick(view: View) {
        authLiveData.value = Pair(0, null)
        authInterface?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authInterface?.onFailure("Email e/ou senha invalido")

        } else {
            firebaseAuth.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener { auth ->
                    if (auth.isSuccessful) {
                        authInterface?.onSuccess()

                    } else {
                        authInterface?.onFailure(auth.exception?.message)
                    }
                }
        }
    }

    fun verifyUserLoggedIn(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun retrievePass(view: View) {
        authLiveData.value = Pair(0, null)
        authInterface?.onStarted()

        if (email.isNullOrEmpty()) {
            authInterface?.onFailure("Digite seu email")
        } else {
            firebaseAuth.sendPasswordResetEmail(email!!).addOnCompleteListener {
                if (it.isSuccessful) {
                    authInterface?.onFailure("Email de recuperação enviado")
                } else {
                    authInterface?.onFailure(it.exception?.message)
                }
            }
        }
    }
}