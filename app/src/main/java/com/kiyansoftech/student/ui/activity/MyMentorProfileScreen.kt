package com.kiyansoftech.student.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import kotlinx.android.synthetic.main.layout_mymentorprofile.*

class MyMentorProfileScreen : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.layout_mymentorprofile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lblRequestSent.setOnClickListener {
            startActivity(Intent(this@MyMentorProfileScreen,MyMentorshipProfile::class.java))

        }


    }
}
