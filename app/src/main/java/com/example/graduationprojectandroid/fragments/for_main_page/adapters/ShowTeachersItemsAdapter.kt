package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutTeachersItemBinding
import com.example.graduationprojectandroid.network.DataService

class ShowTeachersItemsAdapter (
    private var items_arr: ArrayList<TeacherItem>
) : RecyclerView.Adapter<ShowTeachersItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutTeachersItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: TeacherItem)
                = with(binding) {

            DataService.setOtherUserFacePicture(item.login, picture)
            header.text = item.login
            levelInfo.text = App.getAppResources().getString(R.string.level) + " " + item.level.toString()

            // TODO: Обновляй список после каждого маленького изменения текста

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        return ItemView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_teachers_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.bind(items_arr[position])
    }
}