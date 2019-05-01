package com.jorbital.gymjorb.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.QuerySnapshot
import com.jorbital.gymjorb.data.Routine
import com.jorbital.gymjorb.data.RoutineDao
import com.jorbital.gymjorb.utils.FirestoreQueryLiveData

class RoutinesViewModel(dao: RoutineDao) : ViewModel() {

    private val routinesQuery = dao.userRoutines()

    var userRoutines: MutableList<Routine> = mutableListOf()

    private val userRoutineQueryLiveData = FirestoreQueryLiveData(routinesQuery)
    fun getUserRoutineLiveData(): LiveData<QuerySnapshot> {
        return userRoutineQueryLiveData
    }

    fun setRoutinesList(query: QuerySnapshot) {
        userRoutines.clear()
        for (document in query.documents) {
            val test = document.toObject(Routine::class.java)
            if (test != null)
                userRoutines.add(test)
        }
    }
}