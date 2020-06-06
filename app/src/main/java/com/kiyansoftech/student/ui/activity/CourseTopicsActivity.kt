package com.kiyansoftech.student.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contestee.extention.hide
import com.contestee.extention.invisible
import com.contestee.extention.visible
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.adapter.MyCourseTopicsAdapter
import com.kiyansoftech.student.adapter.MyCoursesAdapter
import com.kiyansoftech.student.model.CourseTopics.CourseTopic
import com.kiyansoftech.student.model.CourseTopics.Data
import com.kiyansoftech.student.model.MyCourses.MyCourse
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.activity_course_topics.*
import kotlinx.android.synthetic.main.fragment_my_courses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseTopicsActivity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_course_topics
    }

    var courseid: String? = ""

    private var topicslist: ArrayList<Data> = ArrayList()
    private lateinit var adapter: MyCourseTopicsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    var progressbar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        courseid = intent.extras?.getString("courseid")

        var rvmycoursetopics = findViewById<RecyclerView>(R.id.rvmycoursetopics)
        progressbar = findViewById(R.id.progress)

        linearLayoutManager = LinearLayoutManager(applicationContext)
        rvmycoursetopics.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        gettopics()
    }

    private fun gettopics() {
        progressbar?.visible()
        Networking.with().getServices().getCourseTopics(courseid.toString())
            .enqueue(object : Callback<CourseTopic> {
                override fun onFailure(call: Call<CourseTopic>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<CourseTopic>,
                    response: Response<CourseTopic>
                ) {
                    if (response.body()?.status == 0) {
                        progressbar?.hide()
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {

                        topicslist = response.body()?.data as ArrayList<Data>
                        adapter = MyCourseTopicsAdapter(topicslist)
                        rvmycoursetopics.adapter = adapter
                        progressbar?.invisible()
                        if (topicslist.size.equals(0)) {
                            Toast.makeText(applicationContext, "No Topics Found", Toast.LENGTH_LONG)
                                .show()
                        }
                        //       Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
/*
                        goToActivityAndClearTask<Dashboard>()
*/
                    }
                }
            })

    }

}
