package com.kiyansoftech.student.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.contestee.extention.inflate
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.MyClassmates.Data
import kotlinx.android.synthetic.main.layout_mytutor.view.*
import kotlinx.android.synthetic.main.list_myclassmates.view.*

class MyClassmateadapter(private val classmates: ArrayList<Data>) :
    RecyclerView.Adapter<MyClassmateadapter.ClassmateHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClassmateadapter.ClassmateHolder {
        val inflatedView = parent.inflate(R.layout.list_myclassmates, false)
        return ClassmateHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return classmates.size
    }

    override fun onBindViewHolder(holder: MyClassmateadapter.ClassmateHolder, position: Int) {
        val itemPhoto = classmates[position]
        holder.bindtutor(itemPhoto)
    }


    class ClassmateHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
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
            view.txtmyclassmate.text = data.getName()

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