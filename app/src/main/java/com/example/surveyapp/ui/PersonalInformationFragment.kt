package com.example.surveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.surveyapp.databinding.FragmentPersonalInformationBinding

class PersonalInformationFragment : Fragment() {
    private lateinit var binding: FragmentPersonalInformationBinding
    private val args by navArgs<PersonalInformationFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name: String = args.name
        binding.fragmentPersonalInformationTwName.text = name
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)
        return binding.root
    }
}