package com.jorbital.gymjorb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse

private const val RC_SIGN_IN = 1

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()

        signInOrShowLogin()
    }

    private fun signInOrShowLogin() {
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                this.findNavController().navigate(R.id.action_loginFragment_to_routinesFragment)
            } else {
                if (response == null)
                //TODO: user cancelled, decide what to do
                else {
                    val error = response.error
                    //TODO handle this error
                }
            }
        }
    }
}
