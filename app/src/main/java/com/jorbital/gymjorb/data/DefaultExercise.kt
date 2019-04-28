package com.jorbital.gymjorb.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

data class DefaultExercise(
    val language: String = "en-US",
    val name: String = "",
    val type: Int = ExerciseType.UNKNOWN.value,
    val muscleGroups: List<Int> = emptyList(),
    val keyString: String = ""
)

class DefaultExerciseCreator {

    fun createDefaultExercises() {
        val dbDefaults = FirebaseFirestore.getInstance().collection("defaultExercises")
        val langUS = "en-US"

        val bbbpEng = DefaultExercise(
            langUS,
            "Barbell Bench Press",
            ExerciseType.BARBELL.value,
            listOf(MuscleGroup.CHEST.value, MuscleGroup.TRICEPS.value, MuscleGroup.BICEPS.value),
            "bbbpEng"
        )
        updateFirestore(dbDefaults, bbbpEng)

        val bbibpEng = DefaultExercise(
            langUS,
            "Barbell Incline Bench Press",
            ExerciseType.BARBELL.value,
            listOf(MuscleGroup.CHEST.value, MuscleGroup.TRICEPS.value, MuscleGroup.BICEPS.value),
            "bbibpEng"
        )
        updateFirestore(dbDefaults, bbibpEng)

        val mbcEng = DefaultExercise(
            langUS,
            "Machine Bicep Curl",
            ExerciseType.MACHINE.value,
            listOf(MuscleGroup.BICEPS.value),
            "mbcEng"
        )
        updateFirestore(dbDefaults, mbcEng)

        val mcfEng = DefaultExercise(
            langUS,
            "Machine Chest Fly",
            ExerciseType.MACHINE.value,
            listOf(MuscleGroup.CHEST.value),
            "mcfEng"
        )
        updateFirestore(dbDefaults, mcfEng)

    }

    private fun updateFirestore(collection: CollectionReference, e: DefaultExercise) {
        collection.document(e.keyString).set(e)
    }
}