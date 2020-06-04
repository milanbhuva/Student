package com.kiyansoftech.student.ui.fragment.BottomFragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kiyansoftech.student.R
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment(),View.OnClickListener {

    private var root: View? = null
    private var tags:ArrayList<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root=inflater.inflate(R.layout.fragment_profile, container, false)

        setClickEvent()

        return root
    }

    private fun setClickEvent() {
        root!!.btnResume.setOnClickListener(this)
        root!!.btnMyProfile.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
            }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnResume->{
                root!!.btnMyProfile.setBackgroundResource(R.drawable.white_button)
                root!!.btnResume.setBackgroundResource(R.drawable.profile_selected_button)

                root!!.layMyResume.visibility=View.VISIBLE
                root!!.layMyProfile.visibility=View.GONE
            }
            R.id.btnMyProfile->{
                root!!.btnMyProfile.setBackgroundResource(R.drawable.profile_selected_button)
                root!!.btnResume.setBackgroundResource(R.drawable.white_button)

                root!!.layMyResume.visibility=View.GONE
                root!!.layMyProfile.visibility=View.VISIBLE
            }
        }
    }
}
