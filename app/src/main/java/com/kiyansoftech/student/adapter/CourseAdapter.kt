package com.kiyansoftech.student.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.contestee.extention.inflate
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.CourseList.Data
import com.kiyansoftech.student.ui.activity.CourseRegistrationScreen
import com.kiyansoftech.student.utils.MyApplication.Companion.context
import kotlinx.android.synthetic.main.layout_courseslist.view.*

class CourseAdapter (private val courses: ArrayList<Data>) :
    RecyclerView.Adapter<CourseAdapter.CourseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.CourseHolder {
        val inflatedView = parent.inflate(R.layout.layout_courseslist, false)
        return CourseHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CourseAdapter.CourseHolder, position: Int) {
        val itemcourses = courses[position]
        val data=courses.get(position)
        holder.bindcourses(itemcourses)
        holder.itemView.setOnClickListener {

            val intent = Intent(context!!,CourseRegistrationScreen::class.java)
            intent.putExtra("coursename",data.courseName)
            intent.putExtra("coursedesc",data.courseDescription)
            intent.putExtra("courseid",data.id)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)
//            (context as Activity?)!!.overridePendingTransition(0, 0)


        }
    }


    class CourseHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var data: Data? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        fun bindcourses(data: Data) {
            this.data = data
           Glide.with(view.context).load(data.image).into(view.imgVideo)
            view.txtcoursedes.text = data.courseDescription
            view.txtcoursename.text = data.courseName
            view.txtcoursedprice.text = data.discountedPrice
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }

}