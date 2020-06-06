package com.kiyansoftech.student.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.contestee.extention.inflate
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.CourseTopics.Data
import com.kiyansoftech.student.ui.activity.CourseRegistrationScreen
import com.kiyansoftech.student.utils.MyApplication
import kotlinx.android.synthetic.main.layout_courseslist.view.*
import kotlinx.android.synthetic.main.layout_mycoursetopics.view.*

class MyCourseTopicsAdapter  (private val topics: ArrayList<Data>) :
    RecyclerView.Adapter<MyCourseTopicsAdapter.TopicsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCourseTopicsAdapter.TopicsHolder {
        val inflatedView = parent.inflate(R.layout.layout_mycoursetopics, false)
        return TopicsHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun onBindViewHolder(holder: MyCourseTopicsAdapter.TopicsHolder, position: Int) {
        val itemcourses = topics[position]
        val data=topics.get(position)
        holder.bindtopics(itemcourses)
        holder.itemView.setOnClickListener {

            val intent = Intent(MyApplication.context!!, CourseRegistrationScreen::class.java)
        /*    intent.putExtra("coursename",data.courseName)
            intent.putExtra("coursedesc",data.courseDescription)
            intent.putExtra("courseid",data.id)*/
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            MyApplication.context!!.startActivity(intent)
//            (context as Activity?)!!.overridePendingTransition(0, 0)


        }
    }


    class TopicsHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
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