package com.kiyansoftech.student.ui.fragment.BottomFragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.kiyansoftech.student.R
import com.kiyansoftech.student.ui.fragment.BottomFragments.ExamPortalFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.MentorFragment
import com.kiyansoftech.student.ui.fragment.SideFragments.LocationFInderFragment
import com.kiyansoftech.student.ui.fragment.SideFragments.MyCalenderFragment
import com.kiyansoftech.student.ui.fragment.SideFragments.MyCoursesFragment
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private var root:View?=null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root=inflater.inflate(R.layout.fragment_home, container, false)

        root!!.relmymentors.setOnClickListener {
            openFragment(MentorFragment.newInstance())

        }
        root!!.relcourse.setOnClickListener {
            openFragment(MyCoursesFragment.newInstance())
        }
        root!!.relevents.setOnClickListener {
            openFragment(MyCalenderFragment.newInstance())
        }
        root!!.rellocation.setOnClickListener {
            openFragment(LocationFInderFragment.newInstance())
        }

        return root
    }

    fun openFragment(fragment: Fragment?) {
        /* val bundle = Bundle()
         bundle.putString("userid", userid)
         fragment?.setArguments(bundle)*/
        val transaction: FragmentTransaction = fragmentManager?.beginTransaction()!!
        transaction.replace(R.id.nav_host_fragment, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }


}
