package com.jorbital.gymjorb.data

data class User(
    val name: String?,
    val id: String = ""
)

data class Routine(
    val name: String?,
    val userId: String = ""
)

