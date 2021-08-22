package com.ecirstea.creepyrabbit.ui.navigation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.ui.navigation.HomeActivity
import com.ecirstea.creepyrabbit.ui.view.ListActivity
import kotlinx.android.synthetic.main.fragment_home.*
private const val TAG= "HomeFragment"
class HomeFragment : Fragment() {
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       btnFriends.setOnClickListener{
           val intent = Intent(context, ListActivity::class.java).apply {
               //putExtra(EXTRA_MESSAGE, message)
           }
           startActivity(intent)
           Log.d(TAG, "onViewCreated: test?")
       }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}