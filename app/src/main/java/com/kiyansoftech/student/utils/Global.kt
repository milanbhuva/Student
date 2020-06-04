package com.Bohni.Globals

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.kiyansoftech.student.utils.MyApplication

class Global(context: Context) {

    private val sharedPreferences: SharedPreferences? =
        MyApplication.context!!.getSharedPreferences(
            "Appleto_Practical",
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

        const val CUSTOMER_MOBILENUMBER = "mobileNumber"
        const val CUSTOMER_USERID = "userid"
        const val CUSTOMER_NAME = "name"
        const val CUSTOMER_EMAIL = "email"
        const val CUSTOMER_PHONE = "phone"
        const val CUSTOMER_ADDRESS = "address"
        const val CUSTOMER_STREET = "street"
        const val CUSTOMER_LATITUDE = "latitude"
        const val CUSTOMER_LONGITUDE = "longitude"
        const val CUSTOMER_CITY = "city"
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