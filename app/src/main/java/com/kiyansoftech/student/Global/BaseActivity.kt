package com.kiyansoftech.student.Global

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kiyansoftech.student.utils.SessionManager

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun getLayout(): Int
    lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN )
        setContentView(getLayout())
        session = SessionManager(this)

    }



}
