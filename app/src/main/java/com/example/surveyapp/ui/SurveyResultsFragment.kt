package com.example.surveyapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.surveyapp.R
import com.example.surveyapp.databinding.FragmentSurveyResultsBinding

class SurveyResultsFragment : Fragment() {
    private lateinit var binding: FragmentSurveyResultsBinding
    private val argsSurveyInfo by navArgs<SurveyResultsFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    goToStartpage()
                }
            }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    private fun goToStartpage() {
        findNavController().navigate(
            R.id.entryPageFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(findNavController().graph.startDestinationId, true)
                .build()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSurveyResultsBinding.inflate(inflater, container, false)
        binding.fragmentSurveyResultsTvName.text = argsSurveyInfo.surveyInfo.personalInfoModel.name
        binding.fragmentSurveyResultsTvMail.text = argsSurveyInfo.surveyInfo.personalInfoModel.email
        binding.fragmentSurveyResultsTvAge.text = argsSurveyInfo.surveyInfo.personalInfoModel.age
        binding.fragmentSurveyResultsTvGender.text =
            argsSurveyInfo.surveyInfo.personalInfoModel.gender

        binding.fragmentSurveyResultsTvQuestion1.text =
            argsSurveyInfo.surveyInfo.Question1
        binding.fragmentSurveyResultsTvQuestion2.text =
            argsSurveyInfo.surveyInfo.Question2
        binding.fragmentSurveyResultsTvQuestion3.text =
            argsSurveyInfo.surveyInfo.Question3
        binding.fragmentSurveyResultsTvQuestion4.text =
            argsSurveyInfo.surveyInfo.Question4
        binding.fragmentSurveyResultsTvQuestion5.text =
            argsSurveyInfo.surveyInfo.Question5
        binding.fragmentSurveyResultsTvQuestion6.text =
            argsSurveyInfo.surveyInfo.Question6

        binding.fragmentEntryPageBtnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    resources.getString(
                        R.string.survey_share_text,
                        argsSurveyInfo.surveyInfo.personalInfoModel.name,
                        argsSurveyInfo.surveyInfo.personalInfoModel.email,
                        argsSurveyInfo.surveyInfo.personalInfoModel.age,
                        argsSurveyInfo.surveyInfo.personalInfoModel.gender,
                        argsSurveyInfo.surveyInfo.Question1,
                        argsSurveyInfo.surveyInfo.Question2,
                        argsSurveyInfo.surveyInfo.Question3,
                        argsSurveyInfo.surveyInfo.Question4,
                        argsSurveyInfo.surveyInfo.Question5,
                        argsSurveyInfo.surveyInfo.Question6
                    )
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        return binding.root
    }
}