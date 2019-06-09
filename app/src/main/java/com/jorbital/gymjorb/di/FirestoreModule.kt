package com.jorbital.gymjorb.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jorbital.gymjorb.data.DefaultExerciseDao
import com.jorbital.gymjorb.data.RoutineDao
import com.jorbital.gymjorb.data.UserExerciseDao
import org.koin.dsl.module

val firestoreModule = module {
    factory { Firebase.firestore }
    factory { FirebaseAuth.getInstance().currentUser!! }
    single { RoutineDao(get()) }
    single { DefaultExerciseDao(get()) }
    single { UserExerciseDao(get()) }
}