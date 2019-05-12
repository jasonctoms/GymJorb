package com.jorbital.gymjorb.views.exercisepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.ExerciseType
import com.jorbital.gymjorb.viewmodels.NewRoutineViewModel
import kotlinx.android.synthetic.main.exercise_picker_item.view.*

class ExercisePickerAdapter(val items: List<NewRoutineViewModel.ExerciseListItem>, val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ExercisePickerAdapter.ExercisePickerViewHolder>() {
    var tracker: SelectionTracker<Long>? = null
    var isSelecting: Boolean = false

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
            itemView.setOnClickListener { itemClick(adapterPosition) }
            itemView.exerciseName.text = exercise.name
            itemView.isActivated = isActivated

            when {
                exercise.type == ExerciseType.BARBELL.value -> {
                    itemView.typeImageBackground.backgroundTintList =
                        itemView.context.getColorStateList(R.color.type_barbell)
                    itemView.exerciseTypeImage.setImageResource(R.drawable.ic_weights)
                }
                exercise.type == ExerciseType.DUMBBELL.value -> {
                    itemView.typeImageBackground.backgroundTintList =
                        itemView.context.getColorStateList(R.color.type_dumbbell)
                    itemView.exerciseTypeImage.setImageResource(R.drawable.ic_weights)
                }
                exercise.type == ExerciseType.MACHINE.value -> {
                    itemView.typeImageBackground.backgroundTintList =
                        itemView.context.getColorStateList(R.color.type_machine)
                    itemView.exerciseTypeImage.setImageResource(R.drawable.ic_machine)
                }
                exercise.type == ExerciseType.BODYWEIGHT.value -> {
                    itemView.typeImageBackground.backgroundTintList =
                        itemView.context.getColorStateList(R.color.type_bodyweight)
                    itemView.exerciseTypeImage.setImageResource(R.drawable.ic_bodyweight)
                }
                exercise.type == ExerciseType.CARDIO.value -> {
                    itemView.typeImageBackground.backgroundTintList =
                        itemView.context.getColorStateList(R.color.type_cardio)
                    itemView.exerciseTypeImage.setImageResource(R.drawable.ic_cardio)
                }
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }
    }
}