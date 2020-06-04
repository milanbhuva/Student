package com.kiyansoftech.student.utils

import android.app.NotificationManager
import android.content.Context
import com.google.gson.Gson
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.Login.LoginData


class SessionManager(val context: Context)  {
    val pref =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    

    var isLoggedIn: Boolean
        get() = pref.contains(KEY_IS_LOGIN) && pref.getBoolean(KEY_IS_LOGIN, false)
        set(isLoggedIn) = storeDataByKey(KEY_IS_LOGIN, isLoggedIn)

    var isRemember:Boolean
        get() = pref.contains(ISREMEMBER) && pref.getBoolean(ISREMEMBER, false)
        set(isPinEnable) = storeDataByKey(ISREMEMBER, isPinEnable)


    var user: LoginData?
        get() {
            val gson = Gson()
            val json = getDataByKey(KEY_USER_INFO, "")
            return gson.fromJson(json, LoginData::class.java)
        }
        set(user) {
            val gson = Gson()
            val json = gson.toJson(user)
            pref.edit().putString(KEY_USER_INFO, json).apply()
            isLoggedIn = true
        }


    @JvmOverloads
    fun getDataByKey(Key: String, DefaultValue: String = ""): String {
        return (if (pref.contains(Key)) {
            pref.getString(Key, DefaultValue)
        } else {
            DefaultValue
        })!!
    }

    fun storeDataByKey(key: String, Value: String) {
        pref.edit().putString(key, Value).apply()
    }

    fun storeDataByKey(key: String, Value: Boolean) {
        pref.edit().putBoolean(key, Value).apply()
    }

    operator fun contains(key: String): Boolean {
        return pref.contains(key)
    }

    fun remove(key: String) {
        pref.edit().remove(key).apply()
    }

    fun clearSession() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()

        val editor = context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit()
        editor.putLong("AppUsage", System.currentTimeMillis())
        editor.clear().apply()

        pref.edit().clear().apply()
    }

    companion object {
        private const val KEY_IS_LOGIN = "isLogin"
        private const val KEY_USER_INFO = "user"
        private const val ISREMEMBER = "isRemember"
    }
}