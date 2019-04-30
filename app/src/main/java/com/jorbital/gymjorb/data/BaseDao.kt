package com.jorbital.gymjorb.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseDao(private val firestore: FirebaseFirestore) {

    abstract val rootCollection: String

    private val user = FirebaseAuth.getInstance().currentUser
    protected val userId = user?.uid

    protected fun db() = firestore.collection(rootCollection)
}