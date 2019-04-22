package com.jorbital.gymjorb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.QuerySnapshot
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.viewModels.RoutinesViewModel

class RoutinesFragment : Fragment() {
    private var userId: String? = null
    private val args: RoutinesFragmentArgs by navArgs()

    private lateinit var vm: RoutinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.let {
            userId = it.userId
        }
        if (userId == null)
            this.findNavController().navigate(R.id.action_routinesFragment_to_loginFragment)
        else
            continueActivity()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_routines, container, false)
    }

    override fun onStart() {
        super.onStart()
        val main = activity as MainActivity
        main.showOrHideAppBar(true)
    }

    private fun continueActivity() {
        vm = ViewModelProviders.of(this).get(RoutinesViewModel::class.java)
        vm.data.observe(this, Observer<QuerySnapshot> { query ->
            val test = query
        })
    }
}
