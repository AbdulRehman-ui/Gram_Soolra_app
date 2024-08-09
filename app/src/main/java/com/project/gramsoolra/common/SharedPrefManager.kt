package com.project.gramsoolra.common

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val preference: SharedPreferences

    init {
        preference = context.getSharedPreferences("App_preference", Context.MODE_PRIVATE)
    }


    fun clearAppPreference() {
        preference.edit().clear().apply()
    }

    var USER_ID: String
        set(value) {
            preference.edit().putString("USER_ID", value).apply()
        }
        get() {
            return preference.getString("USER_ID", "")!!
        }

    var SALT: String
        set(value) {
            preference.edit().putString("SALT", value).apply()
        }
        get() {
            return preference.getString("SALT", "")!!
        }

}
