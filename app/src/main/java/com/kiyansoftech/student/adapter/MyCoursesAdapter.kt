package com.kiyansoftech.student.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.contestee.extention.inflate
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.MyCourses.Data
import com.kiyansoftech.student.ui.activity.CourseTopicsActivity
import com.kiyansoftech.student.utils.MyApplication.Companion.context
import kotlinx.android.synthetic.main.layout_courseslist.view.*
import kotlinx.android.synthetic.main.layout_mycourseslist.view.*

class MyCoursesAdapter (private val mycourses: ArrayList<Data>) :
    RecyclerView.Adapter<MyCoursesAdapter.MyCourseHolder>() {
    var position:Int?=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCoursesAdapter.MyCourseHolder {
        val inflatedView = parent.inflate(R.layout.layout_mycourseslist, false)
        return MyCourseHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return mycourses.size
    }

    override fun onBindViewHolder(holder: MyCoursesAdapter.MyCourseHolder, position: Int) {
        val itemcourses = mycourses[position]
        holder.bindcourses(itemcourses)
        val data=mycourses.get(position)

        holder.itemView.setOnClickListener {
            val intent = Intent(context!!,CourseTopicsActivity::class.java)
            intent.putExtra("coursename",data.courseName)
            intent.putExtra("coursedesc",data.courseDescription)
            intent.putExtra("courseid",data.courseId)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)
        }
    }


    class MyCourseHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var data: Data? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        fun bindcourses(data: Data) {
            this.data = data
            view.txtmycname.text = data.courseName
            view.txtmycdes.text = data.courseDescription
            view.txtcduration.text = data.courseDuration
        }

        //4
        override fun onClick(v: View) {

        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }

}