package com.jorbital.gymjorb.data

data class DefaultExercise(
    val language: String = "en-US",
    val name: String = "",
    val type: Int = ExerciseType.UNKNOWN.value,
    val muscleGroups: List<Int> = emptyList(),
    val keyString: String = ""
)