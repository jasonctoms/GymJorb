package com.jorbital.gymjorb.data

data class DefaultExercise(
    val language: String = "en-US",
    val name: String = "",
    val type: String = "",
    val muscleGroups: IntArray = IntArray(0)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DefaultExercise

        if (language != other.language) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (!muscleGroups.contentEquals(other.muscleGroups)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = language.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + muscleGroups.contentHashCode()
        return result
    }
}