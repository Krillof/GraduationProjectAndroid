package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutInventoryItemBinding
import com.example.graduationprojectandroid.data.Items.InventoryItem

class InventoryItemsAdapter (
    private var fragment_manager: FragmentManager,
    private var items_arr: ArrayList<InventoryItem>
) : RecyclerView.Adapter<InventoryItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutInventoryItemBinding.bind(itemView)

        fun bind(fragment_manager: FragmentManager, inventoryItem: InventoryItem)
            = with(binding) {


            marketItemBackground.visibility = inventoryItem.visibility
            marketItemBackground.visibility = inventoryItem.visibility
            picture.visibility = inventoryItem.visibility


            if (inventoryItem.visibility == View.VISIBLE) {
                DataService.setPictureById(inventoryItem.picture_id, picture)
            }


            if (inventoryItem.visibility == View.VISIBLE)
                layout.setOnClickListener {
                    var df: DialogFragment? = null
                    df = WearDialogue(inventoryItem) {
                        if (it){
                            //TODO: Wear inventoryItem
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