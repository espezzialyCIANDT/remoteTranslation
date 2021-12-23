package com.espezzialy.remotetranslation.network

import com.google.gson.annotations.SerializedName


data class Translations(
    val key: String,
    val value: String
)

data class Response (
    val languageCode: String,
    val values: List<Translations>
)


