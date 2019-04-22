package com.jorbital.gymjorb.data

import com.google.firebase.Timestamp

data class WorkoutEntry(
    val userId: String = "",
    val exerciseId: String = "",
    val routineId: String = "",
    val setNumber: Int = 1,
    val repNumber: Int = 1,
    val weight: Int = 0,
    val unit: String = "kilogram",
    val time: Timestamp? = null,
    val personalBest: Boolean
)