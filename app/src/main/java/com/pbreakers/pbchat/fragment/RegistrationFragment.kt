package com.pbreakers.pbchat.fragment


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.activity.MainActivity
import com.quickblox.auth.QBAuth
import com.quickblox.auth.session.QBSession
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Compte"
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.login, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_login -> {
                tryToSignUp()
                true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun tryToSignUp() {
        val username = edLogin.text.toString()
        val password = edPassword.text.toString()
        val fullName = edPseudo.text.toString()

        val dialog = ProgressDialog(activity).apply {
            setTitle("Creation en cour")
            setMessage("Veuillez patientez s'il vous plait !")
        }

        dialog.show()

        val user = QBUser(username, password).apply {
            this.fullName = fullName
        }

        QBUsers.signUp(user).performAsync(object : QBEntityCallback<QBUser> {
            override fun onSuccess(user: QBUser, bundle: Bundle) {
                dialog.dismiss()
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }

            override fun onError(error: QBResponseException) {
                dialog.dismiss()
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
