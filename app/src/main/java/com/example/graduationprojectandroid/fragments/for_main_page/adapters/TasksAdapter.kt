package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutHabitBinding

class TasksAdapter(
    private var tasks_arr: ArrayList<Task>)

    : RecyclerView.Adapter<TasksAdapter.TaskView>()
{
    class TaskView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutHabitBinding.bind(itemView)
        fun bind(task: Task) = with(binding){
            anotherHeader.text = task.header
            text.text = task.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskView {
        return TaskView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_task, parent, false )
        )
    }

    override fun getItemCount(): Int = tasks_arr.size

    override fun onBindViewHolder(holder: TaskView, position: Int) {
        holder.bind(tasks_arr[position])
    }

}