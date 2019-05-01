package com.jorbital.gymjorb.di

import org.koin.core.module.Module

val appComponent: List<Module> = listOf(firestoreModule, viewModelModule)
