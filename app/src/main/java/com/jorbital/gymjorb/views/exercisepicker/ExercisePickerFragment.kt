package com.jorbital.gymjorb.views.exercisepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
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
        exercisePickerRv.layoutManager = LinearLayoutManager(context)
        val adapter = ExercisePickerAdapter(vm.exercisePickerList)
        exercisePickerRv.adapter = adapter
        val tracker = SelectionTracker.Builder<Long>(
            "exercisePickerSelection",
            exercisePickerRv,
            ExercisePickerItemKeyProvider(exercisePickerRv),
            ExercisePickerItemDetailsLookup(exercisePickerRv),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()
        adapter.tracker = tracker
        adapter.notifyDataSetChanged()
    }

    override fun handleOnBackPressed(): Boolean {
        return false
    }
}
