package com.jorbital.gymjorb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.Routine
import com.jorbital.gymjorb.viewModels.NewRoutineViewModel
import kotlinx.android.synthetic.main.fragment_new_routine.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewRoutineFragment : BaseFragment() {
    override val hasAppBar = true
    private val vm: NewRoutineViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.newRoutine.observe(this, Observer<Routine>{ routine ->
            nameEditText.setText( routine.name)
            //TODO: update toggle buttons
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_routine, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity.setFabIcon(R.drawable.ic_save)
    }

    override fun handleOnBackPressed(): Boolean {
        return false
    }
}
