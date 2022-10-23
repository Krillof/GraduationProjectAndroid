package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutFindTeacherItemBinding
import com.example.graduationprojectandroid.network.DataService
import com.example.graduationprojectandroid.data.Items.TeacherItem

class FindTeachersItemsAdapter (
    private var items_arr: ArrayList<TeacherItem>,
    private var updateListener: ()->Unit
) : RecyclerView.Adapter<FindTeachersItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutFindTeacherItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: TeacherItem, updateListener: ()->Unit)
                = with(binding) {

            DataService.setOtherUserFacePicture(item.login, picture)
            header.text = item.login
            buttonBackgroundRequest.setOnClickListener {
                DataService.makeStudyRequest(item.login){
                    updateListener()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        return ItemView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_find_teacher_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.bind(items_arr[position], updateListener)
    }
}