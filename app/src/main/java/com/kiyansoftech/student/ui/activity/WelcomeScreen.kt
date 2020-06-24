package com.kiyansoftech.student.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiyansoftech.student.R
import com.kiyansoftech.student.ui.activity.welcomeLogin.FeaturedAdapter
import com.kiyansoftech.student.ui.activity.welcomeLogin.WelcomeLoginActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.activity_welcome_login.*

class WelcomeScreen : AppCompatActivity() {
    private var adapter: FeaturedAdapter?=null
    private var list:ArrayList<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initList()

    /*    btnStart.setOnClickListener {
            startActivity(Intent(this,SignupScreen::class.java))
        }*/

        btnskip.setOnClickListener {
            startActivity(Intent(this,EditUserProfileActivity::class.java))
        }
    }

    private fun initList() {
        list= ArrayList()
        for (i in 0 until 10){
            list!!.add("$i")
        }

        rvfeatured.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        adapter= FeaturedAdapter(this,list!!)
        rvfeatured.adapter=adapter
    }


}
