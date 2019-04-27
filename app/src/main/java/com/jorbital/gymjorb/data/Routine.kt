package com.jorbital.gymjorb.data

import java.util.*

data class Routine(
    val userId: String = "",
    val name: String? = null,
    val days: List<Int> = emptyList(),
    val exercises: List<Map<String, String>> = emptyList(),
    val lastCompleted: Date? = null
)