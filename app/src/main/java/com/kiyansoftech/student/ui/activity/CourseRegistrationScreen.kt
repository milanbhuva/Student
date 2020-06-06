package com.kiyansoftech.student.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.ui.fragment.BottomFragments.home.HomeFragment
import com.kiyansoftech.student.ui.fragment.SideFragments.PaymentFragment
import kotlinx.android.synthetic.main.layout_courseregister.*

class CourseRegistrationScreen : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.layout_courseregister
    }

    private var bottomFragment: PaymentFragment? = null
    var frame: FrameLayout? = null
    var coursename: String? = ""
    var coursedesc: String? = ""
    var courseid: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coursename = intent.getStringExtra("coursename")
        coursedesc = intent.getStringExtra("coursedesc")
        courseid = intent.getStringExtra("courseid")
        bottomFragment = PaymentFragment.newInstance()
        frame = findViewById(R.id.mainframe)
        txtCourseSelected.setText(coursename)
        txtOverview.setText(coursedesc)

        rlFifth.setOnClickListener {

            openFragment(bottomFragment)
        }
    }

    fun openFragment(fragment: Fragment?) {
        val bundle = Bundle()
       bundle.putString("courseid", courseid)
        fragment?.setArguments(bundle)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainframe, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
