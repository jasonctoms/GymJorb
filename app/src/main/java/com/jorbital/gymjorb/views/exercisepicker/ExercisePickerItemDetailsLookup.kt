package com.jorbital.gymjorb.views.exercisepicker

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class ExercisePickerItemDetailsLookup(private val rv: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = rv.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (rv.getChildViewHolder(view) as ExercisePickerAdapter.ExercisePickerViewHolder).getItemDetails()
        }
        return null
    }
}