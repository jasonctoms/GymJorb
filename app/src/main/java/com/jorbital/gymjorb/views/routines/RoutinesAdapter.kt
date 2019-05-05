package com.jorbital.gymjorb.views.routines

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.DaysOfWeek
import com.jorbital.gymjorb.data.ORDER
import com.jorbital.gymjorb.data.Routine
import com.jorbital.gymjorb.utils.toast
import kotlinx.android.synthetic.main.routine_list_item.view.*
import java.util.*
import java.util.concurrent.TimeUnit

class RoutinesAdapter(private var items: List<Routine>) : RecyclerView.Adapter<RoutinesAdapter.RoutineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.routine_list_item, parent, false)
        return RoutineViewHolder(v)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        val routine = items[position]
        holder.bind(routine)
    }

    override fun getItemCount(): Int = items.size

    fun updateRoutines(routines: List<Routine>) {
        items = routines
        notifyDataSetChanged()
    }

    inner class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal fun bind(routine: Routine) {
            itemView.setOnClickListener { view -> itemClicked(view) }
            itemView.startRoutineButton.setOnClickListener { view -> buttonClicked(view) }
            itemView.routineName.text = routine.name

            val now = Calendar.getInstance().time
            val then = routine.lastCompleted
            if (then == null)
                itemView.lastPerformed.text = itemView.context.getString(R.string.routines_never)
            else {
                val diff = now.time - then.time
                val days = TimeUnit.MILLISECONDS.toDays(diff)
                itemView.lastPerformed.text = itemView.context.getString(R.string.routines_last_performed, days)
            }

            val weekdays = routine.days
            setWeekdays(weekdays)

            itemView.routineExercisesRV.setHasFixedSize(true)
            itemView.routineExercisesRV.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
            itemView.routineExercisesRV.isNestedScrollingEnabled = false
            val sortedExercises = routine.exercises.sortedBy { x -> x[ORDER] }
            itemView.routineExercisesRV.adapter =
                RoutineExerciseListAdapter(sortedExercises)
        }

        private fun setWeekdays(weekdays: List<Int>) {
            for (day in weekdays) {
                val accent = ResourcesCompat.getColor(itemView.resources, R.color.colorAccent, null)

                when (day) {
                    DaysOfWeek.MONDAY.value -> {
                        itemView.routineMonday.setTextColor(accent)
                        itemView.routineMonday.setTypeface(null, Typeface.BOLD)
                    }
                    DaysOfWeek.TUESDAY.value -> {
                        itemView.routineTuesday.setTextColor(accent)
                        itemView.routineTuesday.setTypeface(null, Typeface.BOLD)
                    }
                    DaysOfWeek.WEDNESDAY.value -> {
                        itemView.routineWednesday.setTextColor(accent)
                        itemView.routineWednesday.setTypeface(null, Typeface.BOLD)
                    }
                    DaysOfWeek.THURSDAY.value -> {
                        itemView.routineThursday.setTextColor(accent)
                        itemView.routineThursday.setTypeface(null, Typeface.BOLD)
                    }
                    DaysOfWeek.FRIDAY.value -> {
                        itemView.routineFriday.setTextColor(accent)
                        itemView.routineFriday.setTypeface(null, Typeface.BOLD)
                    }
                    DaysOfWeek.SATURDAY.value -> {
                        itemView.routineSaturday.setTextColor(accent)
                        itemView.routineSaturday.setTypeface(null, Typeface.BOLD)
                    }
                    DaysOfWeek.SUNDAY.value -> {
                        itemView.routineSunday.setTextColor(accent)
                        itemView.routineSunday.setTypeface(null, Typeface.BOLD)
                    }
                }
            }
        }

        private fun itemClicked(v: View) {
            v.context.toast("This will open the place where you edit a routine.")
        }

        private fun buttonClicked(v: View) {
            v.context.toast("This button start or stop the workout.")
        }
    }
}