package com.kiyansoftech.student.Global

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.kiyansoftech.student.utils.MyApplication

class Global(context: Context) {

    private val sharedPreferences: SharedPreferences? =
        MyApplication.context!!.getSharedPreferences(
            "Student",
            Context.MODE_PRIVATE
        )

    private val prefsEditor = sharedPreferences!!.edit()

    public fun saveString(key: String?, value: String?) {
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    public fun getString(key: String?): String? {
        return sharedPreferences?.getString(key, "") ?: ""
    }


    companion object {
        private var global: Global? = null

        const val VENDER_MOBILENUMBER = "mobileNumber"
        const val VENDER_USERID = "userid"
        const val VENDER_NAME = "name"
        const val VENDER_BUSINESSNAME = "businessname"
        const val VENDER_PHONE = "phone"
        const val VENDER_ADDRESS = "address"
        const val VENDER_LATITUDE = "latitude"
        const val VENDER_LONGITUDE = "longitude"
        const val VENDER_IMAGE = "image"
        const val VENDER_CATEGORY = "category"
        const val FCM_TOKEN = "fcm_token"
        const val LANGUAGE = "language"

        fun isConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected

        }

        fun getInstance(context: Context): Global? {
            if (global == null) {
                global =
                    Global(MyApplication.context!!)
            }
            return global
        }

    }

}