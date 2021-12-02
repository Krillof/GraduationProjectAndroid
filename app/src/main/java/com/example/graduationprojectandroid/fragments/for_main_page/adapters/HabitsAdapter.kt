package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutHabitBinding

class HabitsAdapter(
    private var habits_arr: ArrayList<Habit>)

    : RecyclerView.Adapter<HabitsAdapter.HabitView>()
{
    class HabitView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutHabitBinding.bind(itemView)
        fun bind(habit: Habit) = with(binding){
            if (habit.invisible){
                anotherHeader.visibility = View.INVISIBLE
                text.visibility = View.INVISIBLE
                doneCheckbox.visibility = View.INVISIBLE
                undoneCheckbox.visibility = View.INVISIBLE
            } else {
                anotherHeader.text = habit.header
                text.text = habit.text
            }
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
        holder.bind(habits_arr[position])
    }

}