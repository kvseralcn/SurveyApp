package com.example.surveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.surveyapp.databinding.FragmentEntryPageBinding

class EntryPageFragment : Fragment() {
    private lateinit var binding: FragmentEntryPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntryPageBinding.inflate(inflater, container, false)
        binding.fragmentEntryPageBtnStart.setOnClickListener {
            // findNavController().navigate(R.id.action_entryPageFragment_to_personalInformationFragment)
            findNavController().navigate(
                EntryPageFragmentDirections
                    .actionEntryPageFragmentToPersonalInformationFragment(name = binding.fragmentEntryPageEtName.text.toString())
            )
        }
        return binding.root
    }
}