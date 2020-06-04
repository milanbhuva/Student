package com.kiyansoftech.student.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiyansoftech.student.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnStart.setOnClickListener {
            startActivity(Intent(this,SignupScreen::class.java))
        }

        txtLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}
