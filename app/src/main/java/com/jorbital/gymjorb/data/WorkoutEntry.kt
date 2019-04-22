package com.jorbital.gymjorb.data

import java.util.*

data class WorkoutEntry(
    val userId: String = "",
    val exerciseId: String = "",
    val routineId: String = "",
    val setNumber: Int = 1,
    val repNumber: Int = 1,
    val weight: Int = 0,
    val unit: Int = WeightUnit.KILOGRAMS.value,
    val time: Date? = null,
    val personalBest: Boolean = false
)