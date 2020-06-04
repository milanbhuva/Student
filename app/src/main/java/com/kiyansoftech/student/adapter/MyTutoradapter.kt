package com.kiyansoftech.student.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.contestee.extention.inflate
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.GetMyTutors.Data
import com.kiyansoftech.student.model.GetMyTutors.GetMyTutor
import kotlinx.android.synthetic.main.layout_mytutor.view.*

class MyTutoradapter(private val tutors: ArrayList<Data>) :
    RecyclerView.Adapter<MyTutoradapter.TutorHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTutoradapter.TutorHolder {
        val inflatedView = parent.inflate(R.layout.layout_mytutor, false)
        return TutorHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return tutors.size
    }

    override fun onBindViewHolder(holder: MyTutoradapter.TutorHolder, position: Int) {
        val itemPhoto = tutors[position]
        holder.bindtutor(itemPhoto)
    }


    class TutorHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var data: Data? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        fun bindtutor(data: Data) {
            this.data = data
//            Picasso.with(view.context).load(photo.url).into(view.itemImage)
            view.txttutorname.text = data.name
            view.txttutoremail.text = data.email
            view.txttutorphone.text = data.phone
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }

}