package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutMarketItemBinding
import com.example.graduationprojectandroid.fragments.SaveChangesDialogue
import com.example.graduationprojectandroid.fragments.for_main_page.BuyDialogue
import com.example.graduationprojectandroid.network.NetworkService

class MarketItemsAdapter (
    private var fragment_manager: FragmentManager,
    private var items_arr: ArrayList<MarketItem>
) : RecyclerView.Adapter<MarketItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutMarketItemBinding.bind(itemView)

        fun bind(fragment_manager: FragmentManager, item: MarketItem) = with(binding) {

            moneyText.visibility = item.visibility
            marketItemBackground.visibility = item.visibility
            blackWhiteMoneyLogo.visibility = item.visibility
            moneyText.visibility = item.visibility
            marketItemBackground.visibility = item.visibility
            picture.visibility = item.visibility


            if (item.visibility == View.VISIBLE) {
                NetworkService.getInstance().setPictureById(item.id, picture)
            }


            moneyText.text = item.money.toString()

            if (item.visibility == View.VISIBLE)
                layout.setOnClickListener {
                    var df: DialogFragment? = null
                    df = BuyDialogue(item) {
                        if (it){
                            //TODO: Buy a item by NetworkService
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

    override fun onBindViewHolder(holder: MarketItemsAdapter.ItemView, position: Int) {
        holder.bind(fragment_manager, items_arr[position])
    }
}