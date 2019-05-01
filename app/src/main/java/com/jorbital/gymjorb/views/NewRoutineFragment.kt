package com.jorbital.gymjorb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.viewModels.NewRoutineViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewRoutineFragment : BaseFragment() {
    override val hasAppBar = true
    private val vm: NewRoutineViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_routine, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity.setFabIcon(R.drawable.ic_save)
        vm.test()
    }

    override fun handleOnBackPressed(): Boolean {
        return false
    }
}
