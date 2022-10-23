package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutMarketItemBinding
import com.example.graduationprojectandroid.data.Items.InventoryItem

class MarketItemsAdapter (
    private var fragment_manager: FragmentManager,
    private var items_arr: ArrayList<InventoryItem>
) : RecyclerView.Adapter<MarketItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutMarketItemBinding.bind(itemView)

        fun bind(fragment_manager: FragmentManager, inventoryItem: InventoryItem)
        = with(binding) {
            moneyText.visibility = inventoryItem.visibility
            marketItemBackground.visibility = inventoryItem.visibility
            blackWhiteMoneyLogo.visibility = inventoryItem.visibility
            moneyText.visibility = inventoryItem.visibility
            marketItemBackground.visibility = inventoryItem.visibility
            picture.visibility = inventoryItem.visibility

            if (inventoryItem.visibility == View.VISIBLE) {
                //TODO: check: NetworkService or DataService?
                NetworkService.getInstance().setPictureById(inventoryItem.picture_id, picture)
            }

            moneyText.text = inventoryItem.money.toString()

            if (inventoryItem.visibility == View.VISIBLE)
                layout.setOnClickListener {
                    var df: DialogFragment? = null
                    df = BuyDialogue(inventoryItem) {
                        if (it){
                            //TODO: Buy an inventoryItem by DataService
                        }

                        df?.dismiss()
                    }
                    df.show(fragment_manager, "save_changes")
                }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketItemsAdapter.ItemView {
        return MarketItemsAdapter.ItemView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_market_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.bind(fragment_manager, items_arr[position])
    }
}