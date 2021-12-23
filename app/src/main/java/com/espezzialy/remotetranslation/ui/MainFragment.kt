package com.espezzialy.remotetranslation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ContentFrameLayout
import androidx.fragment.app.Fragment
import com.espezzialy.remotetranslation.R
import com.espezzialy.remotetranslation.infra.SampleStringsGenerator
import com.espezzialy.remotetranslation.network.NetworkUtils.Companion.getResponse
import com.espezzialy.remotetranslation.network.Response
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.reword.Reword
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback

class MainFragment : Fragment() {

    lateinit var mresponse: List<Response>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("restring", "Locale Strings Starting")
        getData()
        val localeStrings = AppLocale.supportedLocales.map { it.language + "-" + it.country }
        val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_dropdown_item_1line, localeStrings)

        spinner.adapter = adapter

    }

    override fun onResume() {
        super.onResume()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("restring", "Item Selected Started")
                AppLocale.desiredLocale = AppLocale.supportedLocales[position]

                val rootView =
                        requireActivity()
                                .window
                                .decorView
                                .findViewById<ContentFrameLayout>(android.R.id.content)
                //Mudança Dinamica, > Versatilidade, < Estabilidade
                Reword.reword(rootView)

                languageIdentifier.text = resources.getText(R.string.languageIdentifier)
                Log.i("restring", "Item Selected Finished")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    fun getData() {
        val getTranslation = getResponse()
        Log.i("restring", "Locale Strings Getting Response")

        getTranslation.enqueue(object: Callback<List<Response>> {
            override fun onResponse(
                call: Call<List<Response>>,
                response: retrofit2.Response<List<Response>>
            ) {
                mresponse = response.body()!!
                Log.i("restring", "Locale Response Getted")
                setRestring()
            }

            override fun onFailure(call: Call<List<Response>>, t: Throwable) {}

        })
    }

    fun setRestring() {
        Log.i("restring", "Locale Strings Converting and Setting")
        AppLocale.supportedLocales.forEach { locale ->
            mresponse.forEach {
                if (it.languageCode == locale.toString()) {
                    val strings = SampleStringsGenerator.convertToMap(it.values)
                    Restring.putStrings(locale, strings)
                }
            }
        }
        Log.i("restring", "Locale Strings Converted and Setted")
    }
}

