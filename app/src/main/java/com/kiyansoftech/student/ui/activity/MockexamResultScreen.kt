package com.kiyansoftech.student.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R

class MockexamResultScreen : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.layout_checkresult
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
