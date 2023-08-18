package com.qco.octopusapp.language

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*

@Suppress("DEPRECATION")
class Language(val context: Context) {

    //--------------------this function return current language, if you need it uncomment it--------------------//
    /**fun getLanguage():String{
        return Locale.getDefault().language
    }**/

    //--------------------changing app Language to Arabic or English according to the value of the parameter named code--------------------//
    fun updateLanguage(code : String){
        val locale = Locale(code)
        Locale.setDefault(locale)
        val resources:Resources = context.resources
        val configuration : Configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration,resources.displayMetrics)
    }
}