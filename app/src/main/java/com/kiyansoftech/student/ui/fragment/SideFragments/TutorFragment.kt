package com.kiyansoftech.student.ui.fragment.SideFragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.contestee.extention.hide
import com.contestee.extention.visible
import com.kiyansoftech.student.R
import com.kiyansoftech.student.adapter.MyTutoradapter
import com.kiyansoftech.student.model.GetMyTutors.Data
import com.kiyansoftech.student.model.GetMyTutors.GetMyTutor
import com.kiyansoftech.student.utils.SessionManager
import com.oeye.network.Networking
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
 * Use the [MyCoursesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TutorFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var userid: String  = ""

    private var tutorslist: ArrayList<Data> = ArrayList()

    private lateinit var adapter: MyTutoradapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    var preferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
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
        val view = inflater.inflate(R.layout.fragment_my_tutor, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        var rvmytutors = view.findViewById<RecyclerView>(R.id.rvmytutorlist)
        progressbar = view.findViewById<ProgressBar>(R.id.progresstutor)
        rvmytutors.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        getmytutor()
        return view

    }

    private fun getmytutor() {
        progressbar?.visible()
        Networking.with().getServices().getMyTutors(userid)
            .enqueue(object : Callback<GetMyTutor> {
                override fun onFailure(call: Call<GetMyTutor>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<GetMyTutor>,
                    response: Response<GetMyTutor>
                ) {
                    if (response.body()?.status == 0) {
                        progressbar?.hide()
                        Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                    } else {

                        tutorslist = response.body()?.data as ArrayList<Data>
                        adapter = MyTutoradapter(tutorslist)
                        rvmytutorlist.adapter = adapter
                        progressbar?.hide()
                        Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
/*
                        goToActivityAndClearTask<Dashboard>()
*/
                    }
                }
            })

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TutorFragment().apply {
            }

    }
}
