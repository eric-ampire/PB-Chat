package com.pbreakers.pbchat.fragment.home


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

import com.pbreakers.pbchat.R


class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appCompatActivity = activity as AppCompatActivity

        appCompatActivity.supportActionBar?.title = "Profile"
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.navigation_edit -> {
                true
            }

            R.id.navigation_logout -> {
                AlertDialog.Builder(activity as AppCompatActivity)
                    .setTitle("Deconnexion")
                    .setMessage("Etes vous sur de vouloir quitter ?")
                    .setNegativeButton("Non") { dialog, b ->
                        dialog.dismiss()
                    }.setPositiveButton("Oui") { _, _ ->
                        tryToLogout()
                    }.show()


                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun tryToLogout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu, menu)
    }

}
