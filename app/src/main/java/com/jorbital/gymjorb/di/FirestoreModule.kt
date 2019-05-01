package com.jorbital.gymjorb.di

import com.google.firebase.firestore.FirebaseFirestore
import com.jorbital.gymjorb.data.DefaultExerciseDao
import com.jorbital.gymjorb.data.RoutineDao
import com.jorbital.gymjorb.data.UserExerciseDao
import org.koin.dsl.module

val firestoreModule = module {
    factory { FirebaseFirestore.getInstance() }
    single { RoutineDao(get()) }
    single { DefaultExerciseDao(get()) }
    single { UserExerciseDao(get()) }
}