package com.example.graduationprojectandroid.fragments.for_main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentInventoryBinding
import com.example.graduationprojectandroid.databinding.FragmentNewsBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.NewsItemsAdapter
import com.example.graduationprojectandroid.network.DataService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [News.newInstance] factory method to
 * create an instance of this fragment.
 */
class News(
    private val open_reading_news_item: (Int)->Unit
) : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit
        = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        DataService.getNews {
            newsList.layoutManager = LinearLayoutManager(newsList.context)
            newsList.adapter = NewsItemsAdapter(open_reading_news_item, parentFragmentManager, it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(open_reading_news_item: (Int)->Unit) =
            News(open_reading_news_item)
    }
}