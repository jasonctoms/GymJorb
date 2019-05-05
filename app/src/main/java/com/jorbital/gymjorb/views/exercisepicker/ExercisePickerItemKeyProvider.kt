package com.jorbital.gymjorb.views.exercisepicker

import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.widget.RecyclerView

class ExercisePickerItemKeyProvider(private val rv: RecyclerView) : ItemKeyProvider<Long>(SCOPE_MAPPED) {

    override fun getKey(position: Int): Long? {
        return rv.adapter?.getItemId(position)
    }

    override fun getPosition(key: Long): Int {
        val viewHolder = rv.findViewHolderForItemId(key)
        return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
    }
}