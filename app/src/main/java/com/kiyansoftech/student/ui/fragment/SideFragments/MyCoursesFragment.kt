package com.kiyansoftech.student.ui.fragment.SideFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contestee.extention.hide
import com.contestee.extention.invisible
import com.contestee.extention.visible

import com.kiyansoftech.student.R
import com.kiyansoftech.student.adapter.CourseAdapter
import com.kiyansoftech.student.adapter.MyCoursesAdapter
import com.kiyansoftech.student.model.MyCourses.Data
import com.kiyansoftech.student.model.MyCourses.MyCourse
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.fragment_my_courses.*
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
 * Use the [MyCoursesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyCoursesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var userid: String = ""
    private var mycourseslist: ArrayList<Data> = ArrayList()
    private lateinit var adapter: MyCoursesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    var progressbar: ProgressBar? = null
    private lateinit var gridLayoutManager: GridLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   userid = this.getArguments()?.getString("userid").toString()

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_my_courses, container, false)
        var rvmycourses=view.findViewById<RecyclerView>(R.id.rvmycourses)
        progressbar = view.findViewById<ProgressBar>(R.id.progressbar)
        gridLayoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        rvmycourses.layoutManager =gridLayoutManager

       /* linearLayoutManager = LinearLayoutManager(context)
        rvmycourses.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)*/
        getmycourses()
        return view
    }
    private fun getmycourses() {
        progressbar?.visible()
        Networking.with().getServices().getMyCourses("1")
            .enqueue(object : Callback<MyCourse> {
                override fun onFailure(call: Call<MyCourse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<MyCourse>,
                    response: Response<MyCourse>
                ) {
                    if (response.body()?.status == 0) {
                        progressbar?.hide()
                        Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                    } else {

                        mycourseslist = response.body()?.data as ArrayList<Data>
                        adapter = MyCoursesAdapter(mycourseslist)
                        rvmycourses.adapter = adapter
                        progressbar?.invisible()
                        //       Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
/*
                        goToActivityAndClearTask<Dashboard>()
*/
                    }
                }
            })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyCoursesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MyCoursesFragment().apply {
            }

    }
}
