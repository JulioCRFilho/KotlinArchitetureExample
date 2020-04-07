package com.example.uds.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uds.api.AuthInterface
import com.example.uds.model.Schedule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val user = firebaseAuth.currentUser
    val userName: String = user?.displayName ?: ""

    private val firebaseDataBase = FirebaseDatabase.getInstance()
    private val dbRef = firebaseDataBase.getReference("schedules")

    var authInterface: AuthInterface? = null
    val dbStatusLiveData = MutableLiveData<Pair<Int?, String?>>()

    val openSchedulesLiveData: MutableLiveData<ArrayList<Schedule>> = MutableLiveData()
    val doneSchedulesLiveData: MutableLiveData<ArrayList<Schedule>> = MutableLiveData()

    init {
        getData()
    }

    private fun getData() {
        dbStatusLiveData.value = Pair(0, null)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                dbStatusLiveData.value = Pair(2, p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                val list = p0.children.first().children.map { data ->
                    data.getValue(Schedule::class.java)
                }

                organizeItems(list)
            }
        })
    }

    fun organizeItems(items: List<Schedule?>) {
        val openSchedules: ArrayList<Schedule> = ArrayList()
        val doneSchedules: ArrayList<Schedule> = ArrayList()

        items.forEach { item ->
            if (item?.isDone!!) {
                doneSchedules.add(item)
            } else {
                openSchedules.add(item)
            }
        }
        dbStatusLiveData.value = Pair(1, null)

        openSchedulesLiveData.value = openSchedules
        doneSchedulesLiveData.value = doneSchedules
    }

    fun writeToDB() {
        dbStatusLiveData.value = Pair(0, null)
        authInterface?.onStarted()

        val id = dbRef.push().key
        val teste = Schedule(
            id, "teste1", "só testando", "começando o cadastro das pautas"
        )

        dbRef.child(user?.uid!!).child(id!!).setValue(teste).addOnCompleteListener {
            if (it.isSuccessful) {
                authInterface?.onSuccess()
            } else {
                authInterface?.onFailure(it.exception?.message)
            }
        }
    }
}