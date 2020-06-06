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
import com.kiyansoftech.student.model.CourseTopics.Data
import com.kiyansoftech.student.ui.activity.VideoListActivity
import com.kiyansoftech.student.utils.MyApplication
import com.kiyansoftech.student.utils.MyApplication.Companion.context
import kotlinx.android.synthetic.main.layout_mycoursetopics.view.*

class MyCourseTopicsAdapter  (private val topics: ArrayList<Data>) :
    RecyclerView.Adapter<MyCourseTopicsAdapter.VideosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCourseTopicsAdapter.VideosHolder {
        val inflatedView = parent.inflate(R.layout.layout_mycoursetopics, false)
        return VideosHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun onBindViewHolder(holder: MyCourseTopicsAdapter.VideosHolder, position: Int) {
        val itemcourses = topics[position]
        val data=topics.get(position)
        holder.bindtopics(itemcourses)
/*
        holder.itemView.setOnClickListener {

            val intent = Intent(MyApplication.context!!, CourseRegistrationScreen::class.java)
        */
/*    intent.putExtra("coursename",data.courseName)
            intent.putExtra("coursedesc",data.courseDescription)
            intent.putExtra("courseid",data.id)*//*

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            MyApplication.context!!.startActivity(intent)
//            (context as Activity?)!!.overridePendingTransition(0, 0)


        }
*/
    }


    class VideosHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var data: Data? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        fun bindtopics(data: Data) {
            this.data = data
            Glide.with(view.context).load(data.image).into(view.imgthumb)
            view.txtvideocount.text = data.videosCount+" Video"
            view.lblFirst.text = data.topicName
            view.txtlessoncount.text = data.notesCount+" Note"
            view.txtquizcount.text = data.quizCount+" Quiz"

            view.imgVideo2.setOnClickListener {
                val intent = Intent(context!!, VideoListActivity::class.java)

                intent.putExtra("topicid", data.id)

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context!!.startActivity(intent)            }
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