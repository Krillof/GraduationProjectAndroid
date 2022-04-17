package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutInventoryItemBinding
import com.example.graduationprojectandroid.fragments.for_main_page.WearDialogue
import com.example.graduationprojectandroid.network.NetworkService

class InventoryItemsAdapter (
    private var fragment_manager: FragmentManager,
    private var items_arr: ArrayList<Item>
) : RecyclerView.Adapter<InventoryItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutInventoryItemBinding.bind(itemView)

        fun bind(fragment_manager: FragmentManager, item: Item)
            = with(binding) {


            marketItemBackground.visibility = item.visibility
            marketItemBackground.visibility = item.visibility
            picture.visibility = item.visibility


            if (item.visibility == View.VISIBLE) {
                NetworkService.getInstance().setPictureById(item.picture_id, picture)
            }


            if (item.visibility == View.VISIBLE)
                layout.setOnClickListener {
                    var df: DialogFragment? = null
                    df = WearDialogue(item) {
                        if (it){
                            //TODO: Wear item
                        }

                        df?.dismiss()
                    }
                    df.show(fragment_manager, "save_changes")
                }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryItemsAdapter.ItemView {
        return InventoryItemsAdapter.ItemView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_inventory_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: InventoryItemsAdapter.ItemView, position: Int) {
        holder.bind(fragment_manager, items_arr[position])
    }
}