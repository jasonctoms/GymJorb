package com.jorbital.gymjorb.di

import com.jorbital.gymjorb.viewmodels.NewRoutineViewModel
import com.jorbital.gymjorb.viewmodels.RoutinesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RoutinesViewModel(get()) }
    viewModel { NewRoutineViewModel(get(), get(), get(), get()) }
}