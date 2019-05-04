package com.jorbital.gymjorb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.QuerySnapshot
import com.jorbital.gymjorb.data.*
import com.jorbital.gymjorb.utils.FirestoreQueryLiveData
import java.util.*

class NewRoutineViewModel(
    private val routineDao: RoutineDao,
    defaultExerciseDao: DefaultExerciseDao,
    private val userExerciseDao: UserExerciseDao,
    private val currentUser: FirebaseUser
) : ViewModel() {

    val routineExercises: MutableLiveData<List<UserExercise>> by lazy {
        MutableLiveData<List<UserExercise>>()
    }

    var exercisePickerList: List<ExerciseListItem> = emptyList()

    var userExercises: MutableList<UserExercise> = mutableListOf()
    private val userExerciseQuery = userExerciseDao.userCustomExercises()
    private val userExerciseQueryLiveData = FirestoreQueryLiveData(userExerciseQuery)
    fun getUserExerciseLiveData(): LiveData<QuerySnapshot> {
        return userExerciseQueryLiveData
    }

    var defaultExercises: MutableList<DefaultExercise> = mutableListOf()
    private val defaultExerciseQuery = defaultExerciseDao.defaultExercises()
    private val defaultExerciseQueryLiveData = FirestoreQueryLiveData(defaultExerciseQuery)
    fun getDefaultExerciseLiveData(): LiveData<QuerySnapshot> {
        return defaultExerciseQueryLiveData
    }

    fun setUserExerciseList(query: QuerySnapshot) {
        userExercises.clear()
        for (document in query.documents) {
            val exercise = document.toObject(UserExercise::class.java)
            if (exercise != null)
                userExercises.add(exercise)
        }
    }

    fun setDefaultExerciseList(query: QuerySnapshot) {
        defaultExercises.clear()
        for (document in query.documents) {
            val exercise = document.toObject(DefaultExercise::class.java)
            if (exercise != null)
                defaultExercises.add(exercise)
        }
    }

    fun createExerciseListForPicker() {
        val list: MutableList<ExerciseListItem> = mutableListOf()
        for (defaultExercise in defaultExercises) {
            val newItem = ExerciseListItem(
                defaultExercise.name,
                defaultExercise.type,
                defaultExercise.muscleGroups,
                false,
                defaultExercise.keyString
            )
            list.add(newItem)
        }
        for (userExercise in userExercises) {
            val newItem = ExerciseListItem(
                userExercise.name,
                userExercise.type,
                userExercise.muscleGroups,
                true,
                userExercise.keyString
            )
            list.add(newItem)
        }
        exercisePickerList = list.toList()
    }

    //Call this on exiting the picker
    fun addUserExercises(exercises: List<ExerciseListItem>) {
        val currentList = routineExercises.value?.toMutableList()
        for (exercise in exercises) {
            val newExercise = UserExercise(
                currentUser.uid,
                exercise.name,
                exercise.type,
                exercise.muscleGroups,
                60,
                1,
                1,
                exercise.custom,
                UUID.randomUUID().toString()
            )
            currentList?.add(newExercise)
        }
        routineExercises.postValue(currentList?.toList())
    }

    fun saveRoutine(name: String, days: List<Int>) {
        val routineExercises: MutableList<HashMap<String, String>> = mutableListOf()
        this.routineExercises.value?.forEachIndexed { index, exercise ->
            val map: HashMap<String, String> = hashMapOf(
                EXERCISE_ID to exercise.keyString,
                EXERCISE_NAME to exercise.name, SETS to exercise.sets.toString(), REPS to exercise.reps.toString(),
                ORDER to index.toString()
            )
            routineExercises.add(map)
            userExerciseDao.addUserExercise(exercise)
        }
        val newRoutine = Routine(currentUser.uid, name, days, routineExercises, null)
        routineDao.addNewRoutine(newRoutine)
    }

    data class ExerciseListItem(
        val name: String = "",
        val type: Int = ExerciseType.UNKNOWN.value,
        val muscleGroups: List<Int> = emptyList(),
        val custom: Boolean = false,
        val keyString: String = ""
    )
}