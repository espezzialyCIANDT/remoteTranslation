package com.espezzialy.remotetranslation

import android.app.Application
import android.util.Log
import com.espezzialy.remotetranslation.infra.Locales.LOCALE_PORTUGUESE_BRAZIL
import com.espezzialy.remotetranslation.infra.AppLocaleLocaleProvider
import com.espezzialy.remotetranslation.infra.Locales.LOCALE_ESPANNOL_ARGENTINA
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump
import java.util.*


class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppLocale.supportedLocales = listOf(Locale.US, LOCALE_PORTUGUESE_BRAZIL, LOCALE_ESPANNOL_ARGENTINA)
        Log.i("restring", "SampleApp Starting")
        Restring.init(this)
        Restring.localeProvider = AppLocaleLocaleProvider

        Log.i("restring", "SampleApp Ending")
        ViewPump.init(RewordInterceptor)
    }
}