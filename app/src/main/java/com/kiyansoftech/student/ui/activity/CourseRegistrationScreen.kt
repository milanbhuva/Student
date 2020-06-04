package com.kiyansoftech.student.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import kotlinx.android.synthetic.main.layout_courseregister.*

class CourseRegistrationScreen : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.layout_courseregister
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rlFifth.setOnClickListener { finish() }
    }
}
