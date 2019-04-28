package com.jorbital.gymjorb.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorbital.gymjorb.R
import kotlinx.android.synthetic.main.routine_exercise_list_item.view.*

class RoutineExerciseListAdapter(var items: List<Map<String, String>>) :
    RecyclerView.Adapter<RoutineExerciseListAdapter.RoutineExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineExerciseViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.routine_exercise_list_item, parent, false)
        return RoutineExerciseViewHolder(v)
    }

    override fun onBindViewHolder(holder: RoutineExerciseViewHolder, position: Int) {
        val exercise = items[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class RoutineExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal fun bind(exercise: Map<String, String>) {
            itemView.routineExerciseName.text = exercise.getValue("exerciseId")
            itemView.numberOfSets.text = exercise.getValue("reps")
        }

    }
}