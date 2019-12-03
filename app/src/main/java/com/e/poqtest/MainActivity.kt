package com.e.poqtest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.e.poqtest.DataModel.Model
import com.e.poqtest.ViewModels.AndroidViewModel
import com.e.poqtest.interfaces.ItemClickListener
import com.e.poqtest.adapter.PopAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.hasFixedSize()

        getAndroidVersion()

    }

    private fun getAndroidVersion() {


        val mAndroidViewModel = ViewModelProviders.of(this@MainActivity).get(AndroidViewModel::class.java)
        mAndroidViewModel.isLoading.observe(this, Observer {
            it?.let { showLoadingDialog(it) }
        })
        mAndroidViewModel.getData()?.observe(this, Observer<List<Model>> { androidList ->

            Log.e("list",androidList?.size.toString())
            recyclerView.adapter = PopAdapter(this@MainActivity, androidList as ArrayList<Model>, object :
                ItemClickListener {
                override fun onItemClick(pos: Int) {
//                    Toast.makeText(applicationContext, "item $pos clicked", Toast.LENGTH_LONG).show()
                }
            })
        })

    }
    private fun showLoadingDialog(show: Boolean) {
        if (show) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
    }
}

