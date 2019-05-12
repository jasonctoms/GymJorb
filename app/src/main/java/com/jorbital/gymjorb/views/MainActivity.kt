package com.jorbital.gymjorb.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.jorbital.gymjorb.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bar.setOnMenuItemClickListener {
            // Handle actions based on the menu item if I add menu items
            true
        }

        bar.setNavigationOnClickListener {
            val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
            bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
        }
    }

    fun showOrHideAppBar(hasAppBar: Boolean) {
        if (hasAppBar) {
            bar.visibility = View.VISIBLE
            fab.show()
        } else {
            bar.visibility = View.GONE
            fab.hide()
        }
    }

    fun setFabIcon(iconResId: Int) {
        //TODO: the show/hide is due to a bug in the fab library, so it can be removed one day
        fab.hide()
        fab.setImageResource(iconResId)
        fab.show()
    }

    fun signedOutGoToLogin() {
        this.findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_loginFragment)
    }
}
