package com.example.uds.viewModel

import android.util.Log.d
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uds.api.AuthInterface
import com.example.uds.model.Schedule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val user = firebaseAuth.currentUser
    val userName: String = user?.displayName ?: ""

    private val firebaseDataBase = FirebaseDatabase.getInstance()
    private val dbRef = firebaseDataBase.getReference("Schedule")

    var authInterface: AuthInterface? = null
    val dbStatusLiveData = MutableLiveData<Pair<Int?, String?>>()

    private val teste = Schedule(
        "teste1", "só testando", "começando o cadastro das pautas"
    )

    fun writeToDB(view: View) {
        d("tatata", "COMECOU")
        dbStatusLiveData.value = Pair(0, null)
        authInterface?.onStarted()

        dbRef.setValue(teste, (DatabaseReference.CompletionListener { databaseError, _ ->
            if (databaseError != null) {
                authInterface?.onFailure(databaseError.message)
            } else {
                authInterface?.onSuccess()
            }
        }))
    }
}