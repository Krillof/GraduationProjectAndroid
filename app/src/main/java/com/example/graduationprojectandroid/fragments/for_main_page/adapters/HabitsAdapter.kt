package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R

const val resource: Int = R.layout.simple_layout_habit

class HabitsAdapter(
    private var context: Context,
    private var habits_arr: ArrayList<Habit>)

    : RecyclerView.Adapter<HabitsAdapter.HabitView>()
{
    class HabitView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var header: TextView? = null
        var description: TextView? = null
        var checkbox_done: View? = null
        var checkbox_undone: View? = null

        init {
            header = itemView.findViewById(R.id.another_header)
            description = itemView.findViewById(R.id.text)
            checkbox_done = itemView.findViewById(R.id.done_checkbox)
            checkbox_undone = itemView.findViewById(R.id.undone_checkbox)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitView {
        return HabitView(
            LayoutInflater.from(context).inflate(resource, parent, false )
        )
    }

    override fun getItemCount(): Int = habits_arr.size

    override fun onBindViewHolder(holder: HabitView, position: Int) {
        holder.header?.text = habits_arr[position].header
        holder.description?.text = habits_arr[position].text
    }

}