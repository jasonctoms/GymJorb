package com.jorbital.gymjorb.views.routines


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.viewmodels.NewRoutineViewModel
import com.jorbital.gymjorb.views.BaseFragment
import kotlinx.android.synthetic.main.fragment_exercise_details_edit.*
import kotlinx.android.synthetic.main.fragment_new_routine.*
import kotlinx.android.synthetic.main.fragment_new_routine.toolbarTitle
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ExerciseDetailsEditFragment : BaseFragment() {
    override val hasAppBar = false
    private val args: ExerciseDetailsEditFragmentArgs by navArgs()
    private val vm: NewRoutineViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise_details_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exercise = vm.getExerciseAtPosition(args.position)
        toolbarTitle.transitionName = args.transitionName
        toolbarTitle.text = exercise?.name
    }

    override fun handleOnBackPressed(): Boolean {
        return false
    }
}
