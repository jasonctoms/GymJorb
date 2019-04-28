package com.jorbital.gymjorb.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

abstract class BaseFragment : Fragment() {
    protected var userId: String? = null
    abstract val hasAppBar: Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        userId = user?.uid
    }

    override fun onStart() {
        super.onStart()
        val main = activity as MainActivity
        main.showOrHideAppBar(hasAppBar)
    }
}