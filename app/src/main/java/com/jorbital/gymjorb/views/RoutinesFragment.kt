package com.jorbital.gymjorb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.QuerySnapshot
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.Routine
import com.jorbital.gymjorb.viewModels.RoutinesViewModel
import kotlinx.android.synthetic.main.fragment_routines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoutinesFragment : BaseFragment() {
    override val hasAppBar: Boolean = true
    private val vm: RoutinesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (userId == null)
            this.findNavController().navigate(R.id.action_routinesFragment_to_loginFragment)
        else
            continueLoading()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_routines, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity.setFabIcon(R.drawable.ic_add)
    }

    private fun continueLoading() {
        val routinesLiveData = vm.getUserRoutineLiveData()
        routinesLiveData.observe(this, Observer<QuerySnapshot> { query ->
            vm.setRoutinesList(query)
            val routines = vm.userRoutines.toList()
            updateAdapter(routines)
        })
    }

    private fun updateAdapter(routines: List<Routine>) {
        if (routinesRv.adapter == null) {
            routinesRv.setHasFixedSize(true)
            routinesRv.layoutManager = LinearLayoutManager(context)
            val adapter = RoutinesAdapter(routines)
            routinesRv.adapter = adapter
        } else {
            val adapter = routinesRv.adapter as RoutinesAdapter
            adapter.updateRoutines(routines)
        }
    }

    override fun fabClicked() {
        this.findNavController().navigate(R.id.action_routinesFragment_to_newRoutineFragment)
    }

    override fun handleOnBackPressed(): Boolean {
        //There is no "back" from this fragment
        return true
    }
}
