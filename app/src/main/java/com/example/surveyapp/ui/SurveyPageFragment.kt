package com.example.surveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.surveyapp.data.model.InfoModel
import com.example.surveyapp.databinding.FragmentSurveyPageBinding

class SurveyPageFragment : Fragment() {
    private lateinit var binding: FragmentSurveyPageBinding
    private val args by navArgs<SurveyPageFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSurveyPageBinding.inflate(inflater, container, false)
        binding.fragmentSurveyPageTwName.text = args.personalInfo.name.toString()

        binding.fragmentSurveyPageBtnContinue.setOnClickListener {
            val information = InfoModel(
                binding.fragmentSurveyPageEtQuestion1.text.toString(),
                binding.fragmentSurveyPageEtQuestion2.text.toString(),
                binding.fragmentSurveyPageEtQuestion3.text.toString(),
                args.personalInfo
            )
            val action =
                SurveyPageFragmentDirections.actionSurveyPageFragmentToSurveyResultsFragment(
                    information
                )
            findNavController().navigate(action)
        }
        return binding.root
    }
}