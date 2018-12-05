package com.pbreakers.pbchat.fragment.login


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.activity.MainActivity
import com.pbreakers.pbchat.model.FirebaseChatUser
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
        val firebaseAuth = FirebaseAuth.getInstance()
        val task = firebaseAuth.createUserWithEmailAndPassword(username, password)

        task.addOnFailureListener {
            Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

        task.addOnSuccessListener {
            val profileChangeBuilder = UserProfileChangeRequest.Builder().setDisplayName(fullName).build()
            val currentUser = firebaseAuth.currentUser

            val profileUpdaterTask = currentUser?.updateProfile(profileChangeBuilder)!!
            profileUpdaterTask.addOnSuccessListener {

                saveUserData(dialog)
            }

            profileUpdaterTask.addOnFailureListener {
                dialog.dismiss()
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveUserData(dialog: ProgressDialog) {
        val userCollection = FirebaseFirestore.getInstance().collection("users")
        val currentUser = FirebaseAuth.getInstance().currentUser!!

        val user = FirebaseChatUser(
            uid = currentUser.uid,
            displayName = currentUser.displayName ?: "Undefined"
        )

        userCollection.document(user.uid).set(user).addOnSuccessListener {
            dialog.dismiss()
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()

        }.addOnFailureListener {
            dialog.dismiss()
            Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
        }
    }
}
