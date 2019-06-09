package com.jorbital.gymjorb.views

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.jorbital.gymjorb.R

abstract class BaseFragment : Fragment() {
    protected var userId: String? = null
    abstract val hasAppBar: Boolean
    open val fabDrawableId: Int = R.drawable.ic_add

    protected lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        userId = user?.uid
        mainActivity = activity as MainActivity

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            backPressed()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.showOrHideAppBar(hasAppBar)
        if (hasAppBar)
            mainActivity.setFabIcon(fabDrawableId)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { fabClicked() }
    }

    open fun fabClicked() {
        //do not do anything in base implementation
    }

    open fun backPressed() {
        findNavController().popBackStack()
    }
}
