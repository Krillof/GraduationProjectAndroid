package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutStudyRequestsBinding
import com.example.graduationprojectandroid.network.DataService

class StudyRequestsAdapter (
    private var items_arr: ArrayList<StudentItem>,
    private var updateListener: ()->Unit
) : RecyclerView.Adapter<StudyRequestsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutStudyRequestsBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: StudentItem, updateListener: ()->Unit)
                = with(binding) {

            DataService.setOtherUserFacePicture(item.login, picture)
            header.text = item.login
            buttonBackgroundAccept.setOnClickListener {
                DataService.acceptStudyRequest(item.login){
                    updateListener()
                }
            }
            buttonBackgroundAbandon.setOnClickListener {
                DataService.abandonStudyRequest(item.login){
                    updateListener()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        return ItemView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_study_requests, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.bind(items_arr[position], updateListener)
    }
}