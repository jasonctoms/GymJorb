package com.jorbital.gymjorb.views.routines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.UserExercise
import kotlinx.android.synthetic.main.new_routine_exercise_list_item.view.*

class NewRoutineExerciseAdapter(private var items: List<UserExercise>, val itemClick: (Int, FragmentNavigator.Extras) -> Unit) :
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
            itemView.setOnClickListener { itemClicked() }
            itemView.exerciseName.text = exercise.name
            itemView.numSets.text = exercise.sets.toString()
            itemView.numReps.text = exercise.reps.toString()
            itemView.numTimer.text = exercise.timerValue.toString()

            itemView.newExerciseCard.transitionName = BACKGROUND_TRANSITION + adapterPosition
            itemView.exerciseName.transitionName = TITLE_TRANSITION + adapterPosition
            itemView.numSets.transitionName = SETS_TRANSITION + adapterPosition
            itemView.numReps.transitionName = REPS_TRANSITION + adapterPosition
            itemView.numTimer.transitionName = TIMER_TRANSITION + adapterPosition

        }

        private fun itemClicked(){

            val extras = FragmentNavigatorExtras(
                itemView.newExerciseCard to itemView.newExerciseCard.transitionName,
                itemView.exerciseName to itemView.exerciseName.transitionName,
                itemView.numSets to itemView.numSets.transitionName,
                itemView.numReps to itemView.numReps.transitionName,
                itemView.numTimer to itemView.numTimer.transitionName
            )
            itemClick(adapterPosition, extras)
        }
    }
}