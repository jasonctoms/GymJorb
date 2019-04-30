package com.jorbital.gymjorb.di

import com.google.firebase.firestore.FirebaseFirestore
import com.jorbital.gymjorb.data.RoutineDao
import com.jorbital.gymjorb.viewModels.RoutinesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val firestoreModule = module {
    factory { FirebaseFirestore.getInstance() }
    single { RoutineDao(get()) }
    viewModel { RoutinesViewModel(get()) }
}