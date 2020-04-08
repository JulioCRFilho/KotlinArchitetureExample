package com.example.uds.viewModel

import android.view.View
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

        dbRef.child(user?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                dbStatusLiveData.value = Pair(2, p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                val list = p0.children.map { data ->
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

    fun updateSchedule(id: String, value: Boolean) {
        dbStatusLiveData.value = Pair(0, null)
        authInterface?.onStarted()

        dbRef.child(user?.uid!!).child(id).child("done").setValue(!value).addOnCompleteListener {
            if (it.isSuccessful) {
                authInterface?.onSuccess()
            } else {
                authInterface?.onFailure(it.exception?.message)
            }
        }
    }

    fun writeToDB(title: String, intro: String, desc: String) {
        val id = dbRef.push().key
        val schedule = Schedule(
            id, title, intro, desc
        )

        dbRef.child(user?.uid!!).child(id!!).setValue(schedule)
    }
}