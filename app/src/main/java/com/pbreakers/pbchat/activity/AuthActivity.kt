package com.pbreakers.pbchat.activity

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.fragment.login.LoginFragment
import com.pbreakers.pbchat.fragment.login.RegistrationFragment
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_login -> {
                replaceFragment(supportFragmentManager, LoginFragment())
                true
            }
            R.id.navigation_create_account -> {
                replaceFragment(supportFragmentManager, RegistrationFragment())
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        navigation.setOnNavigationItemSelectedListener(this)
        replaceFragment(supportFragmentManager, LoginFragment())
    }

    companion object {
        fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit()
        }
    }
}
