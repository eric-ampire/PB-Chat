package com.pbreakers.pbchat.activity

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.fragment.home.ContactFragment
import com.pbreakers.pbchat.fragment.home.DiscussionFragment
import com.pbreakers.pbchat.fragment.home.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.navigation_dialog -> {
                AuthActivity.replaceFragment(supportFragmentManager, DiscussionFragment(), R.id.homeFrame)
                true
            }

            R.id.navigation_profile -> {
                AuthActivity.replaceFragment(supportFragmentManager, ProfileFragment(), R.id.homeFrame)
                true
            }

            R.id.navigation_contact -> {
                AuthActivity.replaceFragment(supportFragmentManager, ContactFragment(), R.id.homeFrame)
                true
            }

            else -> {
                false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeNavigation.setOnNavigationItemSelectedListener(this)
        AuthActivity.replaceFragment(supportFragmentManager, DiscussionFragment(), R.id.homeFrame)
    }
}
