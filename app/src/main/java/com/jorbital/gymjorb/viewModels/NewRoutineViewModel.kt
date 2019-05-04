package com.jorbital.gymjorb.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorbital.gymjorb.data.*

class NewRoutineViewModel(
    private val routineDao: RoutineDao,
    private val defaultExerciseDao: DefaultExerciseDao,
    private val userExerciseDao: UserExerciseDao
) : ViewModel() {

    val newRoutine: MutableLiveData<Routine> by lazy {
        MutableLiveData<Routine>()
    }

    fun saveRoutine() {
        val routine = newRoutine.value
    }

}