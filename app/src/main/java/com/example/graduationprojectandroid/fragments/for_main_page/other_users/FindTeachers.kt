package com.example.graduationprojectandroid.fragments.for_main_page.other_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentFindTeachersBinding
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.fragments.Input
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.FindTeachersItemsAdapter
import com.example.graduationprojectandroid.network.DataService


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

    private fun findTeachers(loginStartsWith: String){
        val context = this
        DataService.getTeachersToFind(loginStartsWith){
            binding.teachersList.layoutManager = LinearLayoutManager(
                binding.teachersList.context
            )
            binding.teachersList.adapter = FindTeachersItemsAdapter(it){
                context.findTeachers(
                    loginStartsWith
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceState)

        findTeachers("")

        fragmentManager?.commit {
            val headerFragment: Header = Header.newInstance(
                getString(R.string.find_teachers)
            )
            replace(R.id.header, headerFragment)

            val inputFragment: Input = Input.newInstance(
                getString(R.string.find_by_login),
                ""
            ){
                // after text changed to it
                findTeachers(it)
            }
            replace(R.id.search_input, inputFragment)
        }
    }

    override fun onStop() {
        App.hideKeyboardFrom(this.context, this.view)
        super.onStop()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FindTeachers()
    }
}