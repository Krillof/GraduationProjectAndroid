package com.example.graduationprojectandroid.fragments.for_main_page.dos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentHabitsListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.*
import com.example.graduationprojectandroid.network.DataService


/**
 * A simple [Fragment] subclass.
 * Use the [HabitsList.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitsList(
    private var listener: (h: Habit?) -> Unit
) : Fragment() {

    private lateinit var binding: FragmentHabitsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitsListBinding.inflate(layoutInflater)
        return binding.root //.inflate(, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {

        super.onViewCreated(view, savedInstanceState)


        addButton.setOnClickListener {
            listener(null)
        }

        DataService.getHabits{
            val habitsListAdapter = HabitsAdapter(it, listener)

            habitsList.layoutManager = LinearLayoutManager(habitsList.context)
            habitsList.adapter = habitsListAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(listener: (h: Habit?) -> Unit) =
            HabitsList(listener)
    }
}