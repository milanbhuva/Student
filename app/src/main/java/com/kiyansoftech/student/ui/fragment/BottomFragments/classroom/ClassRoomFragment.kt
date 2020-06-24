package com.kiyansoftech.student.ui.fragment.BottomFragments.classroom

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.kiyansoftech.student.R
import com.kiyansoftech.student.ui.activity.CoursePlayActivity
import com.kiyansoftech.student.ui.fragment.BottomFragments.ExamPortalFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.QuiznTestFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.library.LibraryFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.notifications.NotificationsFragment
import com.kiyansoftech.student.ui.fragment.SideFragments.ClassmatesFragment
import com.kiyansoftech.student.ui.fragment.SideFragments.TutorFragment
import kotlinx.android.synthetic.main.fragment_class_room.view.*


class ClassRoomFragment : Fragment() {

    private var root: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_class_room, container, false)
        root!!.relmylecture.setOnClickListener {
            startActivity(Intent(activity, CoursePlayActivity::class.java))
        }
        root!!.relmytutor.setOnClickListener {
            openFragment(TutorFragment.newInstance())
        }
        root!!.rellibrary.setOnClickListener {
            openFragment(LibraryFragment.newInstance())

        }
        root!!.relmyclassmate.setOnClickListener {
            openFragment(ClassmatesFragment.newInstance())

        }
        root!!.relmyquiz.setOnClickListener {
            openFragment(QuiznTestFragment.newInstance())

        }
        root!!.relmyexam.setOnClickListener {
            openFragment(ExamPortalFragment.newInstance())

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
            ClassRoomFragment().apply {

            }
    }
}
