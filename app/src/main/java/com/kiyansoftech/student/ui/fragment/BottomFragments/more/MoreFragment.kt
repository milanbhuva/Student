package com.kiyansoftech.student.ui.fragment.BottomFragments.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kiyansoftech.student.R


class MoreFragment : Fragment() {

    private var root: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root=inflater.inflate(R.layout.fragment_more, container, false)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MoreFragment().apply {

            }
    }
}
