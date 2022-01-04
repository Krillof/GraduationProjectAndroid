package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutCreatingSubtaskBinding
import com.example.graduationprojectandroid.databinding.SimpleLayoutSubtaskBinding

class CreatingSubtaskAdapter(
    private var subtasks_arr: MutableList<Subtask>
) : RecyclerView.Adapter<CreatingSubtaskAdapter.SubtaskView>()
{


    class SubtaskView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutCreatingSubtaskBinding.bind(itemView)

        fun bind(pos: Int, arr: MutableList<Subtask>, this_adapter: CreatingSubtaskAdapter)
        = with(binding) {
            val isLast = pos == arr.size - 1
            plusOrClose.setOnClickListener {
                if (isLast){
                    Log.println(Log.INFO, "100000", "123")
                    arr.add(Subtask(false, ""))
                } else {
                    arr.remove(arr[pos])
                }

                this_adapter.notifyDataSetChanged()
            }

            plusOrClose.setBackgroundResource(
                if (isLast)
                    R.drawable.creating_subtask_plus
                else
                    R.drawable.creating_subtask_close
            )

            inputHint.visibility = if (isLast) View.VISIBLE else View.GONE
            inputText.visibility = if (isLast) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtaskView {
        return SubtaskView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_creating_subtask,
                    parent, false )
        )
    }

    override fun getItemCount(): Int = subtasks_arr.size

    override fun onBindViewHolder(holder: SubtaskView, position: Int) {
        holder.bind(position, subtasks_arr, this)
    }

}