package com.jorbital.gymjorb.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

data class UserExercise(
    val userId: String = "",
    val name: String = "",
    val type: Int = ExerciseType.UNKNOWN.value,
    val muscleGroups: List<Int> = emptyList(),
    val timerValue: Int = 60,
    val sets: Int = 1,
    val reps: Int = 1,
    val custom: Boolean = false
)

class UserExerciseDao(firestore: FirebaseFirestore) : BaseDao(firestore) {

    override val rootCollection = "userExercises"

    fun userCustomExercises(): Query =
        db().whereEqualTo(UserExercise::userId.name, userId).whereEqualTo(UserExercise::custom.name, true)
}