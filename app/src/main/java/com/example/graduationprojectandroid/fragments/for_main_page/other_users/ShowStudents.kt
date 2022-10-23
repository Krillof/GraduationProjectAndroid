package com.example.graduationprojectandroid.fragments.for_main_page.other_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentShowStudentsBinding
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.ShowStudentsItemsAdapter
import com.example.graduationprojectandroid.data.Items.StudentItem
import com.example.graduationprojectandroid.network.DataService

/**
 * A simple [Fragment] subclass.
 * Use the [ShowStudents.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowStudents(
    private var studentAssignmentsListener: (StudentItem)->Unit
) : Fragment() {
    lateinit var binding: FragmentShowStudentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowStudentsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun getUserStudents(){
        val context = this
        DataService.getUserStudents {
            binding.studentsList.layoutManager = LinearLayoutManager(
                binding.studentsList.context
            )
            binding.studentsList.adapter = ShowStudentsItemsAdapter(
                requireFragmentManager(), it, studentAssignmentsListener
            ){
                context.getUserStudents()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceState)

        getUserStudents()

        fragmentManager?.commit {
            val headerFragment: Header = Header.newInstance(getString(R.string.show_students))
            replace(R.id.header, headerFragment)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(studentAssignmentsListener: (StudentItem)->Unit) =
            ShowStudents(studentAssignmentsListener)
    }
}