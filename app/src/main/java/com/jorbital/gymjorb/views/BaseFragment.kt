package com.jorbital.gymjorb.views

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.jorbital.gymjorb.R

abstract class BaseFragment : Fragment(), OnBackPressedCallback {
    protected var userId: String? = null
    abstract val hasAppBar: Boolean
    open val fabDrawableId: Int = R.drawable.ic_add

    protected lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        userId = user?.uid
        mainActivity = activity as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.showOrHideAppBar(hasAppBar)
        if (hasAppBar)
            mainActivity.setFabIcon(fabDrawableId)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, this)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { fabClicked() }
    }

    open fun fabClicked() {
        //do not do anything in base implementation
    }
}
