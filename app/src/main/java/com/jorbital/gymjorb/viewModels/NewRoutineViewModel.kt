package com.jorbital.gymjorb.viewModels

import androidx.lifecycle.ViewModel
import com.jorbital.gymjorb.data.*

class NewRoutineViewModel(
    private val routineDao: RoutineDao,
    private val defaultExerciseDao: DefaultExerciseDao,
    private val userExerciseDao: UserExerciseDao
) : ViewModel() {

    fun saveRoutine(name: String, days: List<DaysOfWeek>, exercises: List<UserExercise>) {

    }

    fun test() {
        var test1 = routineDao.userRoutines()
        var test2 = defaultExerciseDao.defaultExercises()
        var test3 = userExerciseDao.userExercises()
    }
}