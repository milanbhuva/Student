package com.kiyansoftech.student.ui.activity.welcomeLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiyansoftech.student.R
import com.kiyansoftech.student.ui.activity.Dashboard
import kotlinx.android.synthetic.main.activity_welcome_login.*

class WelcomeLoginActivity : AppCompatActivity() {

    private var adapter:FeaturedAdapter?=null
    private var list:ArrayList<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_login)

        initList()

        txtHome.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
        }

        txtSkip.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
        }
    }

    private fun initList() {
        list= ArrayList()
        for (i in 0 until 10){
            list!!.add("$i")
        }

        recyclerFeature.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adapter= FeaturedAdapter(this,list!!)
        recyclerFeature.adapter=adapter
    }
}
