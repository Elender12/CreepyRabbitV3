package com.ecirstea.creepyrabbit.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.databinding.ActivityMainBinding
import com.ecirstea.creepyrabbit.ui.adapter.RecyclerAdapter
import com.ecirstea.creepyrabbit.ui.viewmodel.MultimediaFileViewModel
import kotlinx.android.synthetic.main.activity_list.*

private const val TAG = "ListActivity"
class ListActivity: AppCompatActivity(){
    //ModelView items
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this)[MultimediaFileViewModel::class.java]}
    private lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        Log.d(TAG, "onCreate HomeActivity: +")

        //Recycler view
        rvAudioList.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(this)
        rvAudioList.adapter = adapter
        observeData()

    }

    private fun observeData () {
        shimmer_view_container.startShimmer()
        viewModel.getAudios().observe(this, {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}