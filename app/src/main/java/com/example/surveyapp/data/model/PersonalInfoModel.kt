package com.example.surveyapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalInfoModel(
    var name: String,
    var email: String,
    var age: String,
    var gender: String
) : Parcelable