package com.espezzialy.remotetranslation.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://private-b5a568-migrationpoc.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        private val translationsApi = retrofit.create(TranslationService::class.java)


        fun getResponse(): Call<List<Response>> {
            return translationsApi.getTranslations()
        }
    }
}