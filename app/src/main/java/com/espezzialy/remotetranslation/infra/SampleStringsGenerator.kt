package com.espezzialy.remotetranslation.infra

import com.espezzialy.remotetranslation.network.Translations
import dev.b3nedikt.restring.PluralKeyword
import java.util.*

/**
 * Generates example strings
 */
object SampleStringsGenerator {

    fun convertToMap(translationList: List<Translations>): Map<String, CharSequence> {
        val mapConverted = mutableMapOf<String, CharSequence>()
        translationList.forEach {
            mapConverted[it.key] = it.value
        }
        return mapConverted
    }
}