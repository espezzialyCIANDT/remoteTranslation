package com.espezzialy.remotetranslation.network

import retrofit2.Call
import retrofit2.http.GET

interface TranslationService {
    @GET("translationse")
    fun getTranslations(): Call<List<Response>>
}