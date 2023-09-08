package com.example.surveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.surveyapp.R
import com.example.surveyapp.data.model.PersonalInfoModel
import com.example.surveyapp.databinding.FragmentPersonalInformationBinding

class PersonalInformationFragment : Fragment() {
    private lateinit var binding: FragmentPersonalInformationBinding
    private val args by navArgs<PersonalInformationFragmentArgs>()

    lateinit var selectedGender: String
    private lateinit var selectedAge: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set default values.
        selectedGender = requireActivity().getString(R.string.unknown)
        selectedAge = requireActivity().getString(R.string.unknown)

        val name: String = args.name
        binding.fragmentPersonalInformationTwName.text = name
        setOnBackPressedCallBack()
        initGenderSpinner()
        initGenderRadioGroup()
        initListeners()
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun initListeners() {
        binding.fragmentPersonalInformationBtnContinue.setOnClickListener {
            if (binding.fragmentPersonalInformationEtEmail.text.isEmpty()) {
                binding.fragmentPersonalInformationEtEmail.error =
                    getString(R.string.edittext_empty_error)
            } else {
                val information = PersonalInfoModel(
                    binding.fragmentPersonalInformationTwName.text.toString(),
                    binding.fragmentPersonalInformationEtEmail.text.toString(),
                    selectedAge,
                    selectedGender
                )
                val action =
                    PersonalInformationFragmentDirections.actionPersonalInformationFragmentToSurveyPageFragment(
                        information
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun initGenderRadioGroup() {
        val ageList = requireActivity().resources.getStringArray(R.array.age_list)

        binding.fragmentPersonalInformationRadioAgeUnder18.text = ageList[0]
        binding.fragmentPersonalInformationRadioAge1824.text = ageList[1]
        binding.fragmentPersonalInformationRadioAge2534.text = ageList[2]
        binding.fragmentPersonalInformationRadioAge3544.text = ageList[3]
        binding.fragmentPersonalInformationRadioAge4554.text = ageList[4]
        binding.fragmentPersonalInformationRadioAge55AndAbove.text = ageList[5]

        binding.fragmentPersonalInformationRgRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.fragmentPersonalInformation_radio_age_under_18 -> selectedAge = ageList[0]

                R.id.fragmentPersonalInformation_radio_age_18_24 -> selectedAge = ageList[1]

                R.id.fragmentPersonalInformation_radio_age_25_34 -> selectedAge = ageList[2]

                R.id.fragmentPersonalInformation_radio_age_35_44 -> selectedAge = ageList[3]

                R.id.fragmentPersonalInformation_radio_age_45_54 -> selectedAge = ageList[4]

                R.id.fragmentPersonalInformation_radio_age_55_and_above -> selectedAge =
                    ageList[5]

                else -> requireActivity().getString(R.string.unknown)
            }
        }
    }

    private fun initGenderSpinner() {
        val genderList = requireActivity().resources.getStringArray(R.array.gender_list)

        val arrayAdapter = object : ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            genderList
        ) {
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val v: View
                if (position == 0) {
                    val tv = TextView(context)
                    tv.height = 0
                    tv.isVisible = false
                    v = tv
                } else {
                    v = super.getDropDownView(position, null, parent)
                }
                return v
            }
        }
        binding.fragmentPersonalInformationSpCategory.adapter = arrayAdapter
        binding.fragmentPersonalInformationSpCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    selectedGender = genderList[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    selectedGender = requireActivity().getString(R.string.unknown)
                }

            }
    }
}