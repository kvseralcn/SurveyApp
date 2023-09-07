package com.example.surveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.surveyapp.databinding.FragmentSurveyResultsBinding

class SurveyResultsFragment : Fragment() {
    private lateinit var binding: FragmentSurveyResultsBinding

    //private val argsPersonalInfo by navArgs<SurveyPageFragmentArgs>()
    private val argsSurveyInfo by navArgs<SurveyResultsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSurveyResultsBinding.inflate(inflater, container, false)
        binding.fragmentSurveyResultsTwName.text = argsSurveyInfo.surveyInfo.personalInfoModel.name
        binding.fragmentSurveyResultsTwMail.text = argsSurveyInfo.surveyInfo.personalInfoModel.email
        binding.fragmentSurveyResultsTwAge.text = argsSurveyInfo.surveyInfo.personalInfoModel.age
        binding.fragmentSurveyResultsTwGender.text =
            argsSurveyInfo.surveyInfo.personalInfoModel.gender

        binding.fragmentSurveyResultsTwQuestion1.text =
            argsSurveyInfo.surveyInfo.Question1
        binding.fragmentSurveyResultsTwQuestion2.text =
            argsSurveyInfo.surveyInfo.Question2
        binding.fragmentSurveyResultsTwQuestion3.text =
            argsSurveyInfo.surveyInfo.Question3
        return binding.root
    }
}