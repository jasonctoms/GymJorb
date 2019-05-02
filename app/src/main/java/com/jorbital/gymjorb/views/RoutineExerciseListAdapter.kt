package com.jorbital.gymjorb.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.EXERCISE_NAME
import com.jorbital.gymjorb.data.SETS
import kotlinx.android.synthetic.main.routine_exercise_list_item.view.*

class RoutineExerciseListAdapter(private var items: List<HashMap<String, String>>) :
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

        internal fun bind(exercise: HashMap<String, String>) {
            itemView.routineExerciseName.text = exercise.getValue(EXERCISE_NAME)
            itemView.numberOfSets.text =
                itemView.context.getString(R.string.routines_number_sets, exercise.getValue(SETS))
        }
    }
}