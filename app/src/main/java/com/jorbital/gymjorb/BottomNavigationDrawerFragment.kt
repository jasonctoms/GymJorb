package com.jorbital.gymjorb

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottomsheet.view.*

class BottomNavigationDrawerFragment : RoundedBottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_bottomsheet, container, false)

        v.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.signOut -> signOut()
                R.id.nav2 -> context!!.toast("Clicked settings")
                R.id.nav3 -> context!!.toast("Clicked bug report")
            }
            true
        }
        return v
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(context!!)
            .addOnCompleteListener {
                dismiss()
                val main = activity as MainActivity
                main.signedOutGoToLogin()
            }
    }

}
