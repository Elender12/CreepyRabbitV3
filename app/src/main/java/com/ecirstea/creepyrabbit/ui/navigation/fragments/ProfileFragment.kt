package com.ecirstea.creepyrabbit.ui.navigation.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.data.model.user.User
import com.ecirstea.creepyrabbit.ui.viewmodel.UserViewModel
import com.ecirstea.creepyrabbit.utils.SharedApp
import kotlinx.android.synthetic.main.fragment_profile.*


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG= "ProfileFragment"
class ProfileFragment : Fragment() {
    private val userViewModel: UserViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_profile, container, false)
        var user: User = User()
        var userId: String
        var fullName : String= "No Name"
        val emailET : EditText? =   inf.findViewById(R.id.editTextEmail)
        val passET : EditText? = inf.findViewById(R.id.editTextUserPassword)
        val saveBtn : Button = inf.findViewById(R.id.btnSave)
        userViewModel.getUserByUsername(SharedApp.prefs.name)
        userViewModel.userModel.observe(viewLifecycleOwner, {
            Log.d(TAG, "onCreateView: ${it?.name}")
            if (it != null) {
                user = it
            }
            fullName = it?.name ?: "No Name"
            var email = it?.email ?: "no@email"
            userId = it?.id ?: ""
            inf.findViewById<TextView>(R.id.tvFullname).text = it?.name ?: "No name"
            val username = it?.username ?: "username"
            val userEmail = it?.email ?: "no email"
            inf.findViewById<TextView>(R.id.tvUsername).setText(username)
            //emailET?.setText(userEmail)
           // emailET?.focusable = View.GONE
            // inf.findViewById<EditText>(R.id.editTextEmail).setText(userEmail)
            //tv.text= fullName

        })
        saveBtn.setOnClickListener{
            val newEmail = emailET?.text
            if(!newEmail.isNullOrBlank()){
                user.email = newEmail.toString()
                Toast.makeText(activity,"saved $newEmail",Toast.LENGTH_LONG).show()
            }
            val newPass = passET?.text
            if(!newPass.isNullOrBlank()){
                user.password = newPass.toString()
                Toast.makeText(activity,"saved $newPass",Toast.LENGTH_LONG).show()
            }

            userViewModel.updateUser(user)

        }
        // Inflate the layout for this fragment
       // val fullNameTxt = container?.findViewById<TextView>(R.id.tvFullname)

        return inf
    }
}