package com.example.graduationprojectandroid.fragments.for_changing_avatar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutAvatarPartBinding
import com.example.graduationprojectandroid.network.DataService

class AvatarPartsAdapter(
    private val amount: Int,
    private val type: Int,
    private var chosen: Int,
    private val choosingListener: (Int, (Int)->Unit)->Unit
) : RecyclerView.Adapter<AvatarPartsAdapter.AvatarPartView>()
{

    private val EMPTIES = 30

    class AvatarPartView(
        var itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutAvatarPartBinding.bind(itemView)
        var isNotDownloadedPicture: Boolean = true

        fun bind(
            type: Int, id: Int, realAmount: Int,
            chosen: Int, choosingListener: (Int) -> Unit
        ) = with(binding) {
            if (id <= realAmount) { // For empty cells
                if (isNotDownloadedPicture) {
                    DataService.setPictureOfAvatarPart(type, id, viewForImage)
                    isNotDownloadedPicture = false
                }

                border.visibility = if (id == chosen) View.VISIBLE else View.GONE

                viewForImage.setOnClickListener {
                    border.visibility = View.GONE
                    choosingListener(id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarPartView {
        return AvatarPartView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_avatar_part, parent, false )
        )
    }

    override fun getItemCount(): Int = (amount + EMPTIES)

    override fun onBindViewHolder(holder: AvatarPartView, position: Int) {
        val context = this
        val id = position + 1
        holder.bind(type, id, amount, chosen){
            choosingListener(id){
                context.notifyItemChanged(chosen-1) // id -> position
                chosen = it
                context.notifyItemChanged(chosen-1) // id -> position

            }
        }
    }

}