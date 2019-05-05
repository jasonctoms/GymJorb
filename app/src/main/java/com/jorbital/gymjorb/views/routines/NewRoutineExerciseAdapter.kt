package com.jorbital.gymjorb.views.routines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.UserExercise
import com.jorbital.gymjorb.utils.toast
import kotlinx.android.synthetic.main.new_routine_exercise_list_item.view.*

class NewRoutineExerciseAdapter(private var items: List<UserExercise>) :
    RecyclerView.Adapter<NewRoutineExerciseAdapter.NewRoutineExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewRoutineExerciseViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.new_routine_exercise_list_item, parent, false)
        return NewRoutineExerciseViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewRoutineExerciseViewHolder, position: Int) {
        val exercise = items[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int = items.size

    fun updateRoutines(exercises: List<UserExercise>) {
        items = exercises
        notifyDataSetChanged()
    }

    inner class NewRoutineExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal fun bind(exercise: UserExercise) {
            itemView.setOnClickListener { view -> itemClicked(view) }
            itemView.exerciseName.text = exercise.name
            itemView.numSets.text = exercise.sets.toString()
            itemView.numReps.text = exercise.reps.toString()
            itemView.numTimer.text = exercise.timerValue.toString()
        }

        private fun itemClicked(v: View) {
            v.context.toast("This will open the exercise editor.")
        }
    }
}