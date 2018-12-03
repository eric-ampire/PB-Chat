package com.pbreakers.pbchat.fragment.login


import android.app.ProgressDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pbreakers.pbchat.R
import com.quickblox.auth.QBAuth
import com.quickblox.auth.session.QBSession
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

    private fun tryToSignIn() {
        val username = edLogin.text.toString()
        val password = edPassword.text.toString()

        val dialog = ProgressDialog(activity).apply {
            setTitle("Connexion")
            setMessage("Veuillez patientez s'il vous plait !")
        }

        dialog.show()

        QBAuth.createSession(QBUser(username, password)).performAsync(object : QBEntityCallback<QBSession> {
            override fun onSuccess(session: QBSession, bundle: Bundle) {
                dialog.dismiss()
                //startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }

            override fun onError(error: QBResponseException) {
                dialog.dismiss()
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
