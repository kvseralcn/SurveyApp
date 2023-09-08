package com.example.surveyapp.ui

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
import com.example.surveyapp.data.model.InfoModel
import com.example.surveyapp.databinding.FragmentSurveyPageBinding

class SurveyPageFragment : Fragment() {

    private lateinit var binding: FragmentSurveyPageBinding
    private val args by navArgs<SurveyPageFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBackPressedCallBack()
    }

    private fun setOnBackPressedCallBack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    redirectToStartPage()
                }
            }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    private fun redirectToStartPage() {
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
    ): View {
        binding = FragmentSurveyPageBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.fragmentSurveyPageBtnContinue.setOnClickListener {
            val information = InfoModel(
                binding.fragmentSurveyPageEtQuestion1.text.toString(),
                binding.fragmentSurveyPageEtQuestion2.text.toString(),
                binding.fragmentSurveyPageEtQuestion3.text.toString(),
                binding.fragmentSurveyPageEtQuestion4.text.toString(),
                binding.fragmentSurveyPageEtQuestion5.text.toString(),
                binding.fragmentSurveyPageEtQuestion6.text.toString(),
                args.personalInfo
            )
            val action =
                SurveyPageFragmentDirections.actionSurveyPageFragmentToSurveyResultsFragment(
                    information
                )
            findNavController().navigate(action)
        }
    }
}