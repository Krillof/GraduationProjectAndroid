package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutNewsItemBinding
import com.example.graduationprojectandroid.data.Items.NewsItem

class NewsItemsAdapter (
    private var choose_one_listener: (Int)->Unit,
    private var fragment_manager: FragmentManager,
    private var items_arr: ArrayList<NewsItem>
) : RecyclerView.Adapter<NewsItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutNewsItemBinding.bind(itemView)

        fun bind(fragment_manager: FragmentManager, item: NewsItem, choose_one_listener: (Int)->Unit)
                = with(binding) {

            newsDate.text = item.date
            header.text = item.header
            infoText.text = item.info_text
            buttonBackground.setOnClickListener {
                choose_one_listener(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemsAdapter.ItemView {
        return NewsItemsAdapter.ItemView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_news_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: NewsItemsAdapter.ItemView, position: Int) {
        holder.bind(fragment_manager, items_arr[position], choose_one_listener)
    }
}