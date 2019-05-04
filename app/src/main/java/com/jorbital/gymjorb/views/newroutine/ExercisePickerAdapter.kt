package com.jorbital.gymjorb.views.newroutine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.viewmodels.NewRoutineViewModel
import kotlinx.android.synthetic.main.exercise_picker_item.view.*

class ExercisePickerAdapter(
    private val items: List<NewRoutineViewModel.ExerciseListItem>
) : RecyclerView.Adapter<ExercisePickerAdapter.ExercisePickerViewHolder>() {
    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisePickerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.exercise_picker_item, parent, false)
        return ExercisePickerViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExercisePickerViewHolder, position: Int) {
        val exercise = items[position]
        tracker?.let {
            holder.bind(exercise, it.isSelected(position.toLong()))
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = position.toLong()

    inner class ExercisePickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal fun bind(exercise: NewRoutineViewModel.ExerciseListItem, isActivated: Boolean = false) {
            itemView.exerciseName.text = exercise.name
            itemView.isActivated = isActivated
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }

    }
}