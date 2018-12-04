package com.pbreakers.pbchat.fragment.login


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.activity.MainActivity
import com.quickblox.auth.QBAuth
import com.quickblox.auth.session.QBSession
import com.quickblox.chat.QBChatService
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.model.QBUser
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Login"
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
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
                tryToSignIn()
                true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    @MainThread
    private fun tryToSignIn() {
        val username = edLogin.text.toString()
        val password = edPassword.text.toString()

        val dialog = ProgressDialog(activity).apply {
            setTitle("Connexion")
            setMessage("Veuillez patientez s'il vous plait !")
        }

        dialog.show()
        val firebaseAuth = FirebaseAuth.getInstance()
        val task = firebaseAuth.signInWithEmailAndPassword(username, password)

        task.addOnFailureListener {
            Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            dialog.show()
        }

        task.addOnSuccessListener {
            dialog.show()
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        }
    }
}
