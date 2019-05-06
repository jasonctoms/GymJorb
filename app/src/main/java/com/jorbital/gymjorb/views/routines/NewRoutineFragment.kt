package com.jorbital.gymjorb.views.routines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.google.firebase.firestore.QuerySnapshot
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.DaysOfWeek
import com.jorbital.gymjorb.data.UserExercise
import com.jorbital.gymjorb.viewmodels.NewRoutineViewModel
import com.jorbital.gymjorb.views.BaseFragment
import kotlinx.android.synthetic.main.fragment_new_routine.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewRoutineFragment : BaseFragment() {
    override val hasAppBar = true
    override val fabDrawableId = R.drawable.ic_save
    private val vm: NewRoutineViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.initRoutineExercises()
        vm.routineExercises.observe(this, Observer<List<UserExercise>> { exercises ->
            updateAdapter(exercises)
        })
        vm.getDefaultExerciseLiveData().observe(this, Observer<QuerySnapshot> { query ->
            vm.setDefaultExerciseList(query)
            vm.createExerciseListForPicker()
        })
        vm.getUserExerciseLiveData().observe(this, Observer<QuerySnapshot> { query ->
            vm.setUserExerciseList(query)
            vm.createExerciseListForPicker()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return inflater.inflate(R.layout.fragment_new_routine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addExercisesButton.setOnClickListener { openPicker() }

        //this makes the shared element transition work coming back
        postponeEnterTransition()
        exercisesRv.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        if (vm.routineExercises.value != null)
            updateAdapter(vm.routineExercises.value!!.toList())
    }

    private fun updateAdapter(exercises: List<UserExercise>) {
        if (exercisesRv.adapter == null) {
            exercisesRv.setHasFixedSize(true)
            exercisesRv.layoutManager = LinearLayoutManager(context)
            val adapter =
                NewRoutineExerciseAdapter(exercises) { position, extras ->
                    openExerciseDetailsEditor(position, extras)
                }
            exercisesRv.adapter = adapter
        } else {
            val adapter = exercisesRv.adapter as NewRoutineExerciseAdapter
            adapter.updateRoutines(exercises)
        }
    }

    private fun openExerciseDetailsEditor(position: Int, extras: FragmentNavigator.Extras) {
        val titleTransition = TITLE_TRANSITION + position
        val backgroundTransition = BACKGROUND_TRANSITION + position
        val setsTransition = SETS_TRANSITION + position
        val repsTransition = REPS_TRANSITION + position
        val timerTransition = TIMER_TRANSITION + position
        val action = NewRoutineFragmentDirections.actionNewRoutineFragmentToExerciseDetailsEditFragment(
            position,
            titleTransition,
            backgroundTransition,
            setsTransition,
            repsTransition,
            timerTransition
        )
        this.findNavController().navigate(action, extras)
    }

    private fun openPicker() {
        this.findNavController().navigate(R.id.action_newRoutineFragment_to_exercisePickerFragment)
    }

    override fun fabClicked() {
        val days: MutableList<Int> = mutableListOf()
        if (checkbox_monday.isChecked)
            days.add(DaysOfWeek.MONDAY.value)
        if (checkbox_tuesday.isChecked)
            days.add(DaysOfWeek.TUESDAY.value)
        if (checkbox_wednesday.isChecked)
            days.add(DaysOfWeek.WEDNESDAY.value)
        if (checkbox_thursday.isChecked)
            days.add(DaysOfWeek.THURSDAY.value)
        if (checkbox_friday.isChecked)
            days.add(DaysOfWeek.FRIDAY.value)
        if (checkbox_saturday.isChecked)
            days.add(DaysOfWeek.SATURDAY.value)
        if (checkbox_sunday.isChecked)
            days.add(DaysOfWeek.SUNDAY.value)

        vm.saveRoutine(nameEditText.text.toString(), days.toList())
        this.findNavController().popBackStack()
    }

    override fun handleOnBackPressed(): Boolean {
        return false
    }
}
