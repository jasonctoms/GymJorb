package com.jorbital.gymjorb.views.routines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.viewmodels.NewRoutineViewModel
import com.jorbital.gymjorb.views.BaseFragment
import kotlinx.android.synthetic.main.fragment_exercise_details_edit.*
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
        exerciseDetailsBackground.transitionName = args.backgroundTransitionName

        toolbarTitle.transitionName = args.titleTransitionName
        toolbarTitle.text = exercise?.name

        setsLayout.transitionName = args.setsTransitionName
        setsEditText.setText( exercise?.sets.toString())

        repsLayout.transitionName = args.repsTransitionName
        repsEditText.setText(exercise?.reps.toString())

        timerLayout.transitionName = args.timerTransitionName
        timerEditText.setText(exercise?.timerValue.toString())

        fab.setOnClickListener { saveChanges() }
    }

    private fun saveChanges(){
        val numSets = setsEditText.text.toString().toInt()
        val numReps = repsEditText.text.toString().toInt()
        val timerValue = timerEditText.text.toString().toInt()
        vm.updateExerciseAtPosition(args.position, numSets, numReps, timerValue)
        this.findNavController().popBackStack()
    }

    override fun handleOnBackPressed(): Boolean {
        return false
    }
}
