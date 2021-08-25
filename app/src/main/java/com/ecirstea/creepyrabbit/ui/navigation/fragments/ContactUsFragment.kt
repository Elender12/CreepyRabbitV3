package com.ecirstea.creepyrabbit.ui.navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.data.model.user.UserFeedback
import com.ecirstea.creepyrabbit.ui.viewmodel.UserViewModel
import com.ecirstea.creepyrabbit.utils.SharedApp
import kotlinx.android.synthetic.main.fragment_contact_us.*
import java.util.*


class ContactUsFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //     val saveBtn : Button = inf.findViewById(R.id.btnSave)
        val inf = inflater.inflate(R.layout.fragment_contact_us, container, false)
        val btnSend : Button = inf.findViewById(R.id.btnSend)
        btnSend.setOnClickListener{
           val userMsj= ET_user_feedback.text.toString()
            val date = Date().toString()
            userViewModel.sendUserMsj(UserFeedback(SharedApp.prefs.name, userMsj, date))
            ET_user_feedback.text.clear()
            Toast.makeText(activity, "Message sent! Thank you!", Toast.LENGTH_LONG).show()
        }
        return inf

    }


}