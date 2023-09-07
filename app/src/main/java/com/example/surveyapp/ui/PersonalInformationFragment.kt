package com.example.surveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.surveyapp.R
import com.example.surveyapp.data.model.PersonalInfoModel
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
        var genderList = arrayOf("Seçiniz", "Kadın", "Erkek", "Diğer")
        var gender: String = "Bilgi alınamadı."

        var age: String = "Bilgi alınamadı."
        binding.fragmentPersonalInformationRgRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.fragmentPersonalInformation_radio_age_under_18 -> age =
                    "18 yaşından küçük"

                R.id.fragmentPersonalInformation_radio_age_18_24 -> age =
                    "18-24 yaş aralığında"

                R.id.fragmentPersonalInformation_radio_age_25_34 -> age =
                    "25-34 yaş aralığında"

                R.id.fragmentPersonalInformation_radio_age_35_44 -> age =
                    "35-44 yaş aralığında"

                R.id.fragmentPersonalInformation_radio_age_45_54 -> age =
                    "45-54 yaş aralığında"

                R.id.fragmentPersonalInformation_radio_age_55_and_above -> age =
                    "55 yaşından büyük"

                else -> "Bilinmeyen yaş aralığı"
            }
        }

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
                    val tw: TextView = TextView(context)
                    tw.height = 0
                    tw.isVisible = false
                    v = tw
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
                    gender = genderList[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    gender = "Bilgi alınamadı."
                }

            }

        binding.fragmentPersonalInformationBtnContinue.setOnClickListener {
            val information = PersonalInfoModel(
                binding.fragmentPersonalInformationTwName.text.toString(),
                binding.fragmentPersonalInformationEtEmail.text.toString(),
                age,
                gender
            )
            val action =
                PersonalInformationFragmentDirections.actionPersonalInformationFragmentToSurveyPageFragment(
                    information
                )
            findNavController().navigate(action)
        }

        return binding.root
    }
}