package com.kiyansoftech.student.ui.fragment.SideFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contestee.extention.hide
import com.contestee.extention.invisible
import com.contestee.extention.visible
import com.kiyansoftech.student.R
import com.kiyansoftech.student.adapter.CourseAdapter
import com.kiyansoftech.student.adapter.MyTutoradapter
import com.kiyansoftech.student.model.CourseList.CourseList
import com.kiyansoftech.student.model.CourseList.Data
import com.kiyansoftech.student.model.GetMyTutors.GetMyTutor
import com.kiyansoftech.student.ui.activity.CourseRegistrationScreen
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.fragment_my_tutor.*
import kotlinx.android.synthetic.main.layout_startcourse.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartCourseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartCourseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    var userid: String = ""
    private var courseslist: ArrayList<Data> = ArrayList()
    private lateinit var adapter: CourseAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    var progressbar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userid = this.getArguments()?.getString("userid").toString()

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_startcourse, container, false)
        var rvcourselist=view.findViewById<RecyclerView>(R.id.rvcourselist)
        progressbar = view.findViewById<ProgressBar>(R.id.progressbar)
        gridLayoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        rvcourselist.layoutManager =gridLayoutManager

        getcourses()

        return view
    }

    private fun getcourses() {
        progressbar?.visible()
        Networking.with().getServices().getCourseList()
            .enqueue(object : Callback<CourseList> {
                override fun onFailure(call: Call<CourseList>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<CourseList>,
                    response: Response<CourseList>
                ) {
                    if (response.body()?.status == 0) {
                        progressbar?.hide()
                        Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                    } else {

                        courseslist = response.body()?.data as ArrayList<Data>
                        adapter = CourseAdapter(courseslist)
                        rvcourselist.adapter = adapter
                        progressbar?.invisible()
                 //       Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
/*
                        goToActivityAndClearTask<Dashboard>()
*/
                    }
                }
            })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lblGetStarted.setOnClickListener {
            val i = Intent(activity, CourseRegistrationScreen::class.java)
            startActivity(i)
            (activity as Activity?)!!.overridePendingTransition(0, 0)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartCourseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            StartCourseFragment().apply {

            }
    }
}
