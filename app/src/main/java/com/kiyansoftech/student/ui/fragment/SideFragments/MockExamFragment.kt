package com.kiyansoftech.student.ui.fragment.SideFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kiyansoftech.student.R
import com.kiyansoftech.student.ui.activity.MockexamResultScreen
import com.kiyansoftech.student.ui.activity.MyMentorProfileScreen
import kotlinx.android.synthetic.main.fragment_mock_exam.*
import kotlinx.android.synthetic.main.layout_mymentors.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MockExamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MockExamFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        return inflater.inflate(R.layout.fragment_mock_exam, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rlSubmitAnswer.setOnClickListener {
            val i = Intent(activity, MockexamResultScreen::class.java)
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
         * @return A new instance of fragment MockExamFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MockExamFragment().apply {

            }
    }
}
