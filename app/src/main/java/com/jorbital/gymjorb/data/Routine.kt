package com.jorbital.gymjorb.data

import java.util.*

data class Routine(
    val userId: String = "",
    val name: String? = null,
    val days: IntArray = IntArray(0),
    val exercises: Array<Map<String, Int>> = emptyArray(),
    val lastCompleted: Date? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Routine

        if (userId != other.userId) return false
        if (name != other.name) return false
        if (!days.contentEquals(other.days)) return false
        if (!exercises.contentEquals(other.exercises)) return false
        if (lastCompleted != other.lastCompleted) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + days.contentHashCode()
        result = 31 * result + exercises.contentHashCode()
        result = 31 * result + (lastCompleted?.hashCode() ?: 0)
        return result
    }
}