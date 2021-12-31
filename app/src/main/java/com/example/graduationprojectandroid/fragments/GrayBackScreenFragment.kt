package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentGrayBackScreenBinding
import com.example.graduationprojectandroid.databinding.FragmentMarketListBinding

private const val ARG_PARAM_SUBFRAGMENT = "subfragment"

/**
 * A simple [Fragment] subclass.
 * Use the [GrayBackScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GrayBackScreenFragment(
    private val subfragment: Fragment
) : Fragment() {

    //TODO: Поправить потом весь код по примеру здесь

    private lateinit var binding: FragmentGrayBackScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGrayBackScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager //TODO: Вот этот пример
            .beginTransaction()
            .add(R.id.subfragment, subfragment)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(subfragment: Fragment) =
            GrayBackScreenFragment(subfragment)
    }
}