package com.jorbital.gymjorb.data

data class UserExercise(
    val userId: String = "",
    val name: String = "",
    val type: Int = ExerciseType.UNKNOWN.value,
    val muscleGroups: List<Int> = emptyList(),
    val timerValue: Int = 60,
    val custom: Boolean = false
)