package com.ecirstea.creepyrabbit.utils

import android.content.Context
import android.content.SharedPreferences
import com.ecirstea.creepyrabbit.utils.Constants.NAME_KEY
import com.ecirstea.creepyrabbit.utils.Constants.SHARED_PREF_FILE
import com.ecirstea.creepyrabbit.utils.Constants.TOKEN_KEY

class Prefs (context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)

    var name: String
        get() = prefs.getString(NAME_KEY, "Anonymous").toString()
        set(value) = prefs.edit().putString(NAME_KEY, value).apply()
    var token: String
        get() = prefs.getString(TOKEN_KEY, "").toString()
        set(value) = prefs.edit().putString(TOKEN_KEY, value).apply()
}