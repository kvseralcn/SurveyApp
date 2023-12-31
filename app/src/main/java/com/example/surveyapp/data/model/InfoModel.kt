package com.example.surveyapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoModel(
    var Question1: String,
    var Question2: String,
    var Question3: String,
    var Question4: String,
    var Question5: String,
    var Question6: String,
    var personalInfoModel: PersonalInfoModel
) : Parcelable