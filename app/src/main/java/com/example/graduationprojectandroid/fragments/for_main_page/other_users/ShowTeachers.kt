package com.example.graduationprojectandroid.fragments.for_main_page.other_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentShowTeachersBinding
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.ShowTeachersItemsAdapter
import com.example.graduationprojectandroid.data.Items.TeacherItem
import com.example.graduationprojectandroid.network.DataService


/**
 * A simple [Fragment] subclass.
 * Use the [ShowTeachers.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowTeachers(
    private val listener: (TeacherItem)->Unit
) : Fragment() {

    lateinit var binding: FragmentShowTeachersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowTeachersBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun getUserTeachers(){
        val context = this
        DataService.getUserTeachers {
            binding.teachersList.layoutManager = LinearLayoutManager(
                binding.teachersList.context
            )
            binding.teachersList.adapter = ShowTeachersItemsAdapter(
                requireFragmentManager(), it, listener
            ){
                context.getUserTeachers()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceState)

        getUserTeachers()

        fragmentManager?.commit {
            val headerFragment: Header = Header.newInstance(getString(R.string.show_teachers))
            replace(R.id.header, headerFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: (TeacherItem)->Unit) =
            ShowTeachers(listener)
    }
}