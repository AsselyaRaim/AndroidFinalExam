package com.example.finalexam.data.models


import com.google.gson.annotations.SerializedName

data class CountryData(
    @SerializedName("Country")
    val country: String,
    @SerializedName("ISO2")
    val iSO2: String,
    @SerializedName("Slug")
    val slug: String
)