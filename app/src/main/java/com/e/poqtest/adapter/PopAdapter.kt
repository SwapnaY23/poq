package com.e.poqtest.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.poqtest.DataModel.Model
import com.e.poqtest.MainActivity
import com.e.poqtest.interfaces.ItemClickListener
import com.e.poqtest.R

import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.items.view.*

class PopAdapter(var context: MainActivity, var mEmpList: ArrayList<Model>, private val itemClick: ItemClickListener): RecyclerView.Adapter<PopAdapter.EmpHolder>()  {

    companion object {
         var mItemClickListener : ItemClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return EmpHolder(view)
    }

    override fun getItemCount(): Int {
        return mEmpList.size
    }

    override fun onBindViewHolder(holder: EmpHolder, position: Int) {
        mItemClickListener = itemClick
        holder.tvFname?.text = mEmpList[position].full_name
        holder.tvLname?.text = mEmpList[position].description

        RxView.clicks(holder.mView).subscribe {
            mItemClickListener!!.onItemClick(position)
        }
    }


    class EmpHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvFname = view.tvFname
        val tvLname = view.tvLname
        val mView = view
    }

}