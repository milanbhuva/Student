package com.kiyansoftech.student.ui.fragment.BottomFragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kiyansoftech.student.R

class HomeFragment : Fragment() {

    private var root:View?=null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root=inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }
}
