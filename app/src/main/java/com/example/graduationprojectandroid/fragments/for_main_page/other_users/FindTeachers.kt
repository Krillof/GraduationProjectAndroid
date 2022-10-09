package com.example.graduationprojectandroid.fragments.for_main_page.other_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentFindTeachersBinding
import com.example.graduationprojectandroid.fragments.SuperDesignInput
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.FindTeachersItemsAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [FindTeachers.newInstance] factory method to
 * create an instance of this fragment.
 */
class FindTeachers : Fragment() {

    lateinit var binding: FragmentFindTeachersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFindTeachersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit
        = with(binding){
        super.onViewCreated(view, savedInstanceState)


        //teachersList.layoutManager = LinearLayoutManager(teachersList.context)
        //teachersList.adapter = FindTeachersItemsAdapter()

        fragmentManager?.commit {
            val superDesignInputFragment: SuperDesignInput = SuperDesignInput.newInstance(
                getString(R.string.find_teachers), true,
                ""
            ){ // after text changed

            }
            replace(R.id.search_input, superDesignInputFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FindTeachers()
    }
}