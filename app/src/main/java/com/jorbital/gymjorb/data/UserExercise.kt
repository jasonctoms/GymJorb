package com.jorbital.gymjorb.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject

data class UserExercise(
    val userId: String = "",
    val name: String = "",
    val type: Int = ExerciseType.UNKNOWN.value,
    val muscleGroups: List<Int> = emptyList(),
    val timerValue: Int = 60,
    val sets: Int = 1,
    val reps: Int = 1,
    val custom: Boolean = false,
    val keyString: String = ""
)

class UserExerciseDao(firestore: FirebaseFirestore) : BaseDao(firestore) {

    override val rootCollection = "userExercises"

    fun userCustomExercises(): Query =
        db().whereEqualTo(UserExercise::userId.name, userId).whereEqualTo(UserExercise::custom.name, true)

    fun mapUserExercises(query: QuerySnapshot): List<UserExercise> {
        val exercises: MutableList<UserExercise> = mutableListOf()
        for (document in query.documents) {
            val exercise = document.toObject<UserExercise>()
            if (exercise != null)
                exercises.add(exercise)
        }
        return exercises.toList()
    }

    fun addUserExercise(exercise: UserExercise) {
        db().document(exercise.keyString).set(exercise)
    }
}