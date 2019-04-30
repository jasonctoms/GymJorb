package com.jorbital.gymjorb.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*

data class Routine(
    val userId: String = "",
    val name: String? = null,
    val days: List<Int> = emptyList(),
    val exercises: List<HashMap<String, String>> = emptyList(),
    val lastCompleted: Date? = null
)

class RoutineDao(firestore: FirebaseFirestore): BaseDao(firestore) {

    override val rootCollection = "routines"

    fun userRoutines(): Query = db()
        .whereEqualTo(Routine::userId.name, userId)
}