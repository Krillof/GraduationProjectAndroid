package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutSubtaskBinding

class SubtaskAdapter(
    private var subtasks_arr: MutableList<ParentizedSubtask>
) : RecyclerView.Adapter<SubtaskAdapter.SubtaskView>()
{


    class SubtaskView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutSubtaskBinding.bind(itemView)

        fun bind(st: ParentizedSubtask, this_adapter: SubtaskAdapter) = with(binding) {
            subtaskLayout.setOnClickListener {
                st.done = !st.done

                st.getParent().notifyDataSetChanged()

                this_adapter.notifyDataSetChanged()
            }

            smallCheckbox.setBackgroundResource(
                if (st.done)
                    R.drawable.small_check
                else
                    R.drawable.small_circle
            )

            smallText.text = st.text

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtaskView {
        return SubtaskView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_subtask, parent, false )
        )
    }

    override fun getItemCount(): Int = subtasks_arr.size

    override fun onBindViewHolder(holder: SubtaskView, position: Int) {
        holder.bind(subtasks_arr[position], this)
    }

}