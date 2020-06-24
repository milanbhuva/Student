package com.kiyansoftech.student.ui.fragment.SideFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contestee.extention.hide
import com.contestee.extention.visible

import com.kiyansoftech.student.R
import com.kiyansoftech.student.adapter.MyClassmateadapter
import com.kiyansoftech.student.adapter.MyTutoradapter
import com.kiyansoftech.student.model.GetMyTutors.GetMyTutor
import com.kiyansoftech.student.model.MyClassmates.Data
import com.kiyansoftech.student.model.MyClassmates.MyClassmate
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.fragment_classmates.*
import kotlinx.android.synthetic.main.fragment_my_tutor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClassmatesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassmatesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
  //  var userid: String = ""
  //  var progressbar: ProgressBar? = null

    private var classmatelist: ArrayList<Data> = ArrayList()
    private lateinit var adapter: MyClassmateadapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // userid = this.getArguments()?.getString("userid").toString()

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
        val view= inflater.inflate(R.layout.fragment_classmates, container, false)
       //var rvmyclassmates = view.findViewById<RecyclerView>(R.id.rvmyclassmatelist)
        //progressbar = view.findViewById<ProgressBar>(R.id.progressclassmate)

/*
        rvmyclassmates.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
*/
      //  getmyclassmates()
        return view
    }
/*
    private fun getmyclassmates() {
        progressbar?.visible()
        Networking.with().getServices().getMyClassmates(userid,userid)
            .enqueue(object : Callback<MyClassmate> {
                override fun onFailure(call: Call<MyClassmate>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<MyClassmate>,
                    response: Response<MyClassmate>
                ) {
                    if (response.body()?.status == 0) {
                        progressbar?.hide()
                        Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                    } else {

                        classmatelist = response.body()?.data as ArrayList<Data>
                        adapter = MyClassmateadapter(classmatelist)
                        rvmyclassmatelist.adapter = adapter
                        progressbar?.hide()
                        Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
*/
/*
                        goToActivityAndClearTask<Dashboard>()
*//*

                    }
                }
            })

    }
*/

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ClassmatesFragment().apply {
            }
    }
}
