package com.jorbital.gymjorb.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

data class Routine(
    val userId: String = "",
    val name: String? = null,
    val days: List<Int> = emptyList(),
    val exercises: List<HashMap<String, String>> = emptyList(),
    val lastCompleted: Date? = null
)

class RoutineDao(firestore: FirebaseFirestore) : BaseDao(firestore) {

    override val rootCollection = "routines"

    fun userRoutines(): Query = db()
        .whereEqualTo(Routine::userId.name, userId)

    fun mapUserRoutines(query: QuerySnapshot): List<Routine> {
        val routines: MutableList<Routine> = mutableListOf()
        for (document in query.documents) {
            val routine = document.toObject(Routine::class.java)
            if (routine != null)
                routines.add(routine)
        }
        return routines.toList()
    }


    fun addNewRoutine(routine: Routine) {
        db().add(routine)
    }
}