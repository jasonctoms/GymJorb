package com.jorbital.gymjorb.views.exercisepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.viewmodels.NewRoutineViewModel
import com.jorbital.gymjorb.views.BaseFragment
import kotlinx.android.synthetic.main.fragment_exercise_picker.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ExercisePickerFragment : BaseFragment() {
    override val hasAppBar = false
    private val vm: NewRoutineViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise_picker, container, false)
    }

    override fun onStart() {
        super.onStart()

        fab.hide()
        fab.setOnClickListener { addSelectedExercises() }

        exercisePickerRv.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        exercisePickerRv.addItemDecoration(decoration)
        val adapter = ExercisePickerAdapter(vm.exercisePickerList) { position -> addSingleExercise(position) }
        exercisePickerRv.adapter = adapter

        val tracker = SelectionTracker.Builder<Long>(
            "exercisePickerSelection",
            exercisePickerRv,
            ExercisePickerItemKeyProvider(exercisePickerRv),
            ExercisePickerItemDetailsLookup(exercisePickerRv),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()
        tracker.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val selectionSize = tracker.selection.size()
                    if (selectionSize > 0) {
                        if (fab.isOrWillBeHidden)
                            fab.show()
                        adapter.isSelecting = true
                    } else if (selectionSize == 0) {
                        if (fab.isOrWillBeShown)
                            fab.hide()
                        adapter.isSelecting = false
                    }
                }
            })
        adapter.tracker = tracker
    }

    private fun addSingleExercise(position: Int) {
        val adapter = exercisePickerRv.adapter as ExercisePickerAdapter
        if (!adapter.isSelecting) {
            val exercise = adapter.items[position]
            vm.addSingleUserExercise(exercise)
            this.findNavController().popBackStack()
        }
    }

    private fun addSelectedExercises() {
        val adapter = exercisePickerRv.adapter as ExercisePickerAdapter
        val tracker = adapter.tracker
        val selection = tracker?.selection
        if (selection != null) {
            val exercises = selection.map {
                adapter.items[it.toInt()]
            }.toList()
            vm.addUserExercises(exercises)
            this.findNavController().popBackStack()
        }
    }

    override fun handleOnBackPressed(): Boolean {
        return false
    }
}
