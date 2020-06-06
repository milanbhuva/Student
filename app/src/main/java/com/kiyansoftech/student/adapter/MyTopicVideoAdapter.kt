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
import com.kiyansoftech.student.model.TopicVideos.Data
import com.kiyansoftech.student.ui.activity.VideoListActivity
import com.kiyansoftech.student.utils.MyApplication.Companion.context
import kotlinx.android.synthetic.main.layout_topicsvideos.view.*

class MyTopicVideoAdapter(private val topicvideos: ArrayList<Data>) :
    RecyclerView.Adapter<MyTopicVideoAdapter.VideosHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyTopicVideoAdapter.VideosHolder {
        val inflatedView = parent.inflate(R.layout.layout_topicsvideos, false)
        return VideosHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return topicvideos.size
    }

    override fun onBindViewHolder(holder: MyTopicVideoAdapter.VideosHolder, position: Int) {
        val itemcourses = topicvideos[position]
        val data = topicvideos.get(position)
        holder.bindvideos(itemcourses)
        holder.itemView.setOnClickListener {

          /*  val intent = Intent(context!!, VideoListActivity::class.java)
            intent.putExtra("videoname", data.videoName)
            intent.putExtra("videolink", data.videoLink)
            intent.putExtra("courseid", data.id)

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)*/
//            (context as Activity?)!!.overridePendingTransition(0, 0)


        }
    }


    class VideosHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var data: Data? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        fun bindvideos(data: Data) {
            this.data = data
            Glide.with(view.context).load(data.thumbnailImage).into(view.imgthumbvid)
            view.txtvidname.text = data.videoName
            view.txtviddes.text = data.videoDescription
            view.txtviddur.text = data.videoDuration


            view.imgthumbvid.setOnClickListener {
                Toast.makeText(view.context, "Cool", Toast.LENGTH_LONG).show()
            }
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