package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutHabitBinding
import com.example.graduationprojectandroid.data.Items.Habit
import com.example.graduationprojectandroid.data.States.HabitDoneStates
import com.example.graduationprojectandroid.network.DataService

class HabitsAdapter(
    private var habits_arr: ArrayList<Habit>,
    var edit_listener: (h: Habit?) -> Unit
    ) : RecyclerView.Adapter<HabitsAdapter.HabitView>()
{


    class HabitView(
        var itemView: View,
        var edit_listener: (h: Habit?) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutHabitBinding.bind(itemView)

        fun bind(habit: Habit, adapter: HabitsAdapter) = with(binding) {

            anotherHeader.text = habit.header
            text.text = habit.text

            doneCheckbox.setOnClickListener {
                habit.done =
                    if (habit.done != HabitDoneStates.DONE)
                        HabitDoneStates.DONE
                    else
                        HabitDoneStates.UNKNOWN

                DataService.setHabitState(habit.id, habit.done)

                adapter.notifyDataSetChanged()
            }

            undoneCheckbox.setOnClickListener {
                habit.done =
                    if (habit.done != HabitDoneStates.UNDONE)
                        HabitDoneStates.UNDONE
                    else
                        HabitDoneStates.UNKNOWN

                DataService.setHabitState(habit.id, habit.done)

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

            text.setOnClickListener {
                edit_listener(habit)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitView {
        return HabitView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_habit, parent, false ),
            edit_listener
        )
    }

    override fun getItemCount(): Int = habits_arr.size

    override fun onBindViewHolder(holder: HabitView, position: Int) {
        holder.bind(habits_arr[position], this)
    }

}