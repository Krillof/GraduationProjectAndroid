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
import com.example.graduationprojectandroid.data.Items.Habit


/**
 * A simple [Fragment] subclass.
 * Use the [HabitsList.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitsList(
    private var loginFrom: String,
    private var loginTo: String,
    private var hideAddButton: Boolean = false,
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
        return binding.root
    }

    private fun updateList(){
        val context = this
        DataService.getHabits(loginFrom, loginTo){
            binding.habitsList.layoutManager = LinearLayoutManager(
                binding.habitsList.context
            )
            binding.habitsList.adapter = HabitsAdapter(it)
            { habit ->
                listener(habit)
                context.updateList()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {

        super.onViewCreated(view, savedInstanceState)

        if (hideAddButton){
            addButton.visibility = View.GONE
            addButtonBackground.visibility = View.GONE
            addButtonPlus.visibility = View.GONE
        } else {
            addButton.setOnClickListener {
                listener(null)
            }
        }

        updateList()
    }

    companion object {
        @JvmStatic
        fun newInstance(loginFrom: String, loginTo: String,
                        hideAddButton: Boolean = false, listener: (h: Habit?) -> Unit) =
            HabitsList(loginFrom, loginTo, hideAddButton, listener)
    }
}