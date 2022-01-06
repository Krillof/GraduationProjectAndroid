package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutTaskBinding

class TasksAdapter(
    private var tasks_arr: ArrayList<ParentizedTask>,
    private var listener: (Task?) -> Unit
)

    : RecyclerView.Adapter<TasksAdapter.TaskView>()
{
    class TaskView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutTaskBinding.bind(itemView)

        fun before() = with(binding){
            subtasksList.layoutManager = LinearLayoutManager(subtasksList.context)
        }

        fun bind(task: ParentizedTask, this_adapter: TasksAdapter, listener: (Task?) -> Unit)
        = with(binding){

            task.setParentAdapterForSubtasks(this_adapter)

            anotherHeader.visibility = task.visibility
            text.visibility = task.visibility
            doneCheckbox.visibility = task.visibility
            subtasksCounterCircle.visibility = task.show_subtasks_always
            subtasksCounter.visibility = task.show_subtasks_always
            subtasksList.visibility = task.getCurrentShowSubtasks()

            anotherHeader.text = task.header
            text.text = task.text

            text.setOnClickListener {
                var value = Task(
                    task.id,
                    task.header,
                    task.text,
                    MutableList(0, {Subtask()}),
                    task.isEveryday,
                    task.isEveryweek,
                    task.isEverymonth,
                    task.difficulty
                )
                value.setParentizedSubtasksAsSubtasks(
                    task.getSubtasks() as MutableList<ParentizedSubtask>
                )
                listener(value)
            }

            doneCheckbox.setBackgroundResource(
                if (task.isDone())
                    R.drawable.tick_in_circle_on
                else
                    R.drawable.tick_in_circle_off
            )


            subtasksList.adapter = task.getAdapter()

            doneCheckbox.setOnClickListener {
                task.setFullDone(! task.isDone())

                this_adapter.notifyDataSetChanged()
            }

            subtasksCounterCircle.setOnClickListener {
                task.setCurrentShowSubtasks(
                    if (task.getCurrentShowSubtasks() == View.GONE)
                        View.VISIBLE
                    else
                        View.GONE
                )

                subtasksList.visibility = task.getCurrentShowSubtasks()
            }

            subtasksCounter
                .text = task.howManySubtasksDone().toString() + "/" + task.getSubtasks().size.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskView {
        val value = TaskView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_task, parent, false )
        )

        value.before()

        return value
    }

    override fun getItemCount(): Int = tasks_arr.size

    override fun onBindViewHolder(holder: TaskView, position: Int) {
        holder.bind(tasks_arr[position],this, listener)
    }

}