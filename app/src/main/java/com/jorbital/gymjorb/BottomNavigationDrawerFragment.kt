package com.jorbital.gymjorb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottomsheet.view.*

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_bottomsheet, container, false)

        v.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav1 -> context!!.toast("Clicked sign out")
                R.id.nav2 -> context!!.toast("Clicked settings")
                R.id.nav3 -> context!!.toast("Clicked bug report")
            }
            true
        }
        return v
    }
}
