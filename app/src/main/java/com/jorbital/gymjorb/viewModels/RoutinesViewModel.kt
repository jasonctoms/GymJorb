package com.jorbital.gymjorb.viewModels

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.jorbital.gymjorb.utils.FirestoreQueryLiveData

class RoutinesViewModel : ViewModel() {
    val db = FirebaseFirestore.getInstance()

    val test = db.collection("routines")
    val query = test.whereEqualTo("userId", "test_user")
    val data = FirestoreQueryLiveData(query)
}