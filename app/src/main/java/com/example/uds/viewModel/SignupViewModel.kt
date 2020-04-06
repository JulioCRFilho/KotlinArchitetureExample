package com.example.uds.viewModel

import android.util.Log.d
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uds.api.AuthInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignupViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    val signupLiveData = MutableLiveData<Pair<Int?, String?>>()

    var authInterface: AuthInterface? = null
    var email: String? = null
    var pass: String? = null
    var confirmPass: String? = null
    var name: String? = null

    fun signUp(view: View) {
        signupLiveData.value = Pair(0, null)
        authInterface?.onStarted()

        if (email.isNullOrEmpty() || pass.isNullOrEmpty() || confirmPass.isNullOrEmpty() || name.isNullOrEmpty()) {
            authInterface?.onFailure("Email, nome e senha obrigatórios")

        } else if (pass != confirmPass) {
            authInterface?.onFailure("As senhas devem coincidir")

        } else {
            firebaseAuth.createUserWithEmailAndPassword(email!!, pass!!)
                .addOnCompleteListener { auth ->
                    if (auth.isSuccessful) {
                        firebaseAuth.currentUser?.let { user ->
                            val userUpdate = UserProfileChangeRequest.Builder()
                            userUpdate.setDisplayName(name)

                            user.updateProfile(userUpdate.build()).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    authInterface?.onSuccess()
                                } else {
                                    authInterface?.onFailure(it.exception?.message)
                                }
                            }
                        }

                    } else {
                        authInterface?.onFailure(auth.exception?.message)

                    }
                }

        }
    }
}