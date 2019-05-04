package com.jorbital.gymjorb.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*

data class DefaultExercise(
    val language: String = "en-US",
    val name: String = "",
    val type: Int = ExerciseType.UNKNOWN.value,
    val muscleGroups: List<Int> = emptyList(),
    val keyString: String = ""
)

class DefaultExerciseDao(firestore: FirebaseFirestore) : BaseDao(firestore) {

    override val rootCollection = "defaultExercises"

    fun defaultExercises(): Query = db().whereEqualTo(DefaultExercise::language.name, Locale.getDefault().toString())
}