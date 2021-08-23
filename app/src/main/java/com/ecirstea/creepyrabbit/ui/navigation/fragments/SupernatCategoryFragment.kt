package com.ecirstea.creepyrabbit.ui.navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.ui.adapter.RecyclerAdapter
import com.ecirstea.creepyrabbit.ui.viewmodel.MultimediaFileViewModel
import com.ecirstea.creepyrabbit.utils.Constants.SUPERNATURAL_CATEGORY
import kotlinx.android.synthetic.main.fragment_supernat_category.*


class SupernatCategoryFragment : Fragment() {

    //ModelView items
    private val viewModel by lazy { ViewModelProvider(this)[MultimediaFileViewModel::class.java]}
    private lateinit var adapter: RecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        adapter = container?.context?.let { RecyclerAdapter(it) }!!
        return inflater.inflate(R.layout.fragment_supernat_category, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        //Recycler view
        rvAudioList.layoutManager = LinearLayoutManager(activity)
        rvAudioList.adapter = adapter
        observeData()
    }
    private fun observeData () {
        shimmer_view_container.startShimmer()
        viewModel.getAudiosByCategory(SUPERNATURAL_CATEGORY).observe(viewLifecycleOwner, {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}
/* */