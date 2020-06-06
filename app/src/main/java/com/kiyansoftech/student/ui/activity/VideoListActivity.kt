package com.kiyansoftech.student.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.contestee.extention.hide
import com.contestee.extention.invisible
import com.contestee.extention.visible
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.adapter.CourseAdapter
import com.kiyansoftech.student.adapter.MyTopicVideoAdapter
import com.kiyansoftech.student.model.CourseList.CourseList
import com.kiyansoftech.student.model.TopicVideos.Data
import com.kiyansoftech.student.model.TopicVideos.TopicVideo
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.activity_video_list.*
import kotlinx.android.synthetic.main.layout_startcourse.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoListActivity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_video_list
    }

    var topicid: String? = ""
    private var videolist: ArrayList<Data> = ArrayList()
    private lateinit var adapter: MyTopicVideoAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    var progressbar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topicid = intent.extras?.getString("topicid")
            progressbar=findViewById(R.id.progress1)
        gridLayoutManager = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        rvtopicvideos.layoutManager =gridLayoutManager
        getvideos()
    }
    private fun getvideos() {
        progressbar?.visible()
        Networking.with().getServices().getTopicVideos(topicid.toString())
            .enqueue(object : Callback<TopicVideo> {
                override fun onFailure(call: Call<TopicVideo>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<TopicVideo>,
                    response: Response<TopicVideo>
                ) {
                    if (response.body()?.status == 0) {
                        progressbar?.hide()
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    } else {

                        videolist = response.body()?.data as ArrayList<Data>
                        adapter = MyTopicVideoAdapter(videolist)
                        rvtopicvideos.adapter = adapter
                        progressbar?.invisible()
                        //       Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
/*
                        goToActivityAndClearTask<Dashboard>()
*/
                    }
                }
            })

    }



}
