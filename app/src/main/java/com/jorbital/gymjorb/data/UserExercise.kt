package com.jorbital.gymjorb.data

data class UserExercise(
    val userId: String = "",
    val name: String = "",
    val type: String = "",
    val muscleGroups: Array<String>,
    val timerValue: Int = 60,
    val custom: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserExercise

        if (userId != other.userId) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (!muscleGroups.contentEquals(other.muscleGroups)) return false
        if (timerValue != other.timerValue) return false
        if (custom != other.custom) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + muscleGroups.contentHashCode()
        result = 31 * result + timerValue
        result = 31 * result + custom.hashCode()
        return result
    }
}