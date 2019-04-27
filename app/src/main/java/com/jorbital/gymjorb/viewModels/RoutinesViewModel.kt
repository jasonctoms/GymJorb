package com.jorbital.gymjorb.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.jorbital.gymjorb.data.Routine
import com.jorbital.gymjorb.utils.FirestoreQueryLiveData

class RoutinesViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val routinesCollection = db.collection("routines")

    private lateinit var userId: String
    private lateinit var userRoutineQuery: Query

    private lateinit var userRoutineQueryLiveData: FirestoreQueryLiveData
    var userRoutines: MutableList<Routine> = mutableListOf()

    fun init(userId: String){
        this.userId = userId
        userRoutineQuery = routinesCollection.whereEqualTo("userId", userId)
        userRoutineQueryLiveData = FirestoreQueryLiveData(userRoutineQuery)
    }

    fun getUserRoutineLiveData(): LiveData<QuerySnapshot>{
        return userRoutineQueryLiveData
    }

    fun setRoutinesList(query: QuerySnapshot){
        userRoutines.clear()
        for (document in query.documents){
            val test = document.toObject(Routine::class.java)
            if (test != null)
                userRoutines.add(test)
        }
    }
}