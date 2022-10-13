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
import com.example.graduationprojectandroid.databinding.FragmentStudyRequestsBinding
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.fragments.Input
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.StudyRequestsAdapter
import com.example.graduationprojectandroid.network.DataService


/**
 * A simple [Fragment] subclass.
 * Use the [StudyRequests.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudyRequests : Fragment() {
    lateinit var binding: FragmentStudyRequestsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyRequestsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun findStudyRequests(loginStartsWith: String)
    {
        val context = this
        DataService.getStudyRequests(loginStartsWith){
            binding.potentialStudentsList.layoutManager = LinearLayoutManager(
                binding.potentialStudentsList.context
            )
            binding.potentialStudentsList.adapter = StudyRequestsAdapter(it) {
                context.findStudyRequests(
                    loginStartsWith
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceState)
        findStudyRequests("")

        fragmentManager?.commit {
            val headerFragment: Header = Header.newInstance(
                getString(R.string.study_requests)
            )
            replace(R.id.header, headerFragment)

            val inputFragment: Input = Input.newInstance(
                getString(R.string.find_by_login),
                ""
            ){
                // after text changed to it
                findStudyRequests(it)
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
            StudyRequests()
    }
}