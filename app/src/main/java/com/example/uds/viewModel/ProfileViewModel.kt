package com.example.uds.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val user = firebaseAuth.currentUser
    val userName: String? = user?.displayName
    val userEmail: String? = user?.email
}