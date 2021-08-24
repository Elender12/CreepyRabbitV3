package com.ecirstea.creepyrabbit.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.databinding.ActivityMainBinding
import com.ecirstea.creepyrabbit.ui.adapter.FavsRecyclerAdapter
import com.ecirstea.creepyrabbit.ui.viewmodel.MultimediaFileViewModel
import kotlinx.android.synthetic.main.activity_list.*

private const val TAG = "ListActivity"
class ListActivity: AppCompatActivity(){
    //ModelView items
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this)[MultimediaFileViewModel::class.java]}
    private lateinit var adapter: FavsRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        Log.d(TAG, "onCreate HomeActivity: +")

        //Recycler view
        rvAudioListFavs.layoutManager = LinearLayoutManager(this)
        adapter = FavsRecyclerAdapter(this)
        rvAudioListFavs.adapter = adapter
        observeData()

    }

    private fun observeData () {
        shimmer_view_container.startShimmer()
        viewModel.getFavsAudios().observe(this, {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}