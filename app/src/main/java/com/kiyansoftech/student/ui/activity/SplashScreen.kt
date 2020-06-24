package com.kiyansoftech.student.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_splash_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashScreen,LoginActivity::class.java))
            finish()
        }, 2000)
*/
        btnexplore.setOnClickListener {
            startActivity(Intent(this@SplashScreen,LoginActivity::class.java))
            finish()
        }
    }
}
