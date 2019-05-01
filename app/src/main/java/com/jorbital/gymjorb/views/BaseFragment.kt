package com.jorbital.gymjorb.views

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.jorbital.gymjorb.R


abstract class BaseFragment : Fragment(), OnBackPressedCallback {
    protected var userId: String? = null
    abstract val hasAppBar: Boolean

    protected lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        userId = user?.uid
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        mainActivity.showOrHideAppBar(hasAppBar)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, this)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { fabClicked() }
    }

    open fun fabClicked() {
        //do not do anything in base implementation
    }
}
