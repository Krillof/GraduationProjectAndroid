package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutHabitBinding

class HabitsAdapter(
    private var habits_arr: ArrayList<Habit>
    ) : RecyclerView.Adapter<HabitsAdapter.HabitView>()
{


    class HabitView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutHabitBinding.bind(itemView)

        fun bind(habit: Habit, adapter: HabitsAdapter) = with(binding) {

            anotherHeader.visibility = habit.visibility
            text.visibility = habit.visibility
            doneCheckbox.visibility = habit.visibility
            undoneCheckbox.visibility = habit.visibility

            anotherHeader.text = habit.header
            text.text = habit.text

            doneCheckbox.setOnClickListener {
                habit.done =
                    if (habit.done != HabitDoneStates.DONE)
                        HabitDoneStates.DONE
                    else
                        HabitDoneStates.UNKNOWN

                adapter.notifyDataSetChanged()
            }

            undoneCheckbox.setOnClickListener {
                habit.done =
                    if (habit.done != HabitDoneStates.UNDONE)
                        HabitDoneStates.UNDONE
                    else
                        HabitDoneStates.UNKNOWN

                adapter.notifyDataSetChanged()
            }


            doneCheckbox.setBackgroundResource(
                when (habit.done){
                    HabitDoneStates.DONE
                    -> R.drawable.tick_in_circle_on
                    else -> R.drawable.tick_in_circle_off
                }
            )

            undoneCheckbox.setBackgroundResource(
                when (habit.done){
                    HabitDoneStates.UNDONE
                    -> R.drawable.minus_in_circle_on
                    else -> R.drawable.minus_in_circle_off
                }
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitView {
        return HabitView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_habit, parent, false )
        )
    }

    override fun getItemCount(): Int = habits_arr.size

    override fun onBindViewHolder(holder: HabitView, position: Int) {
        holder.bind(habits_arr[position], this)
    }

}