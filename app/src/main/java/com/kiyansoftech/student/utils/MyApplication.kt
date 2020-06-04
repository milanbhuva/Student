package com.kiyansoftech.student.utils

import android.app.Application
import android.content.Context


class MyApplication : Application() {

//    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);


    }

    companion object {
        @JvmField
        var context: Context? = null
    }
}