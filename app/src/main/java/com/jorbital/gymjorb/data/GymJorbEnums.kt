package com.jorbital.gymjorb.data

enum class DaysOfWeek(val value: Int) {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),

    UNKNOWN(-1)
}

enum class MuscleGroup(val value: Int) {
    QUADS(1),
    HAMSTRINGS(2),
    CALVES(3),
    CHEST(4),
    BACK(5),
    SHOULDERS(6),
    TRICEPS(7),
    BICEPS(8),
    FOREARMS(9),
    TRAPS(10),
    ABS(11),
    CARDIO(12),

    UNKNOWN(-1)
}

enum class WeightUnit(val value: Int) {
    POUNDS(1),
    KILOGRAMS(2),

    UNKNOWN(-1)
}

enum class ExerciseType(val value: Int) {
    BARBELL(1),
    DUMBBELL(2),
    MACHINE(3),
    CARDIO(4),
    OTHER(5),

    UNKNOWN(-1)
}