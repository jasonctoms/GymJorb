package com.jorbital.gymjorb.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.jorbital.gymjorb.R
import com.jorbital.gymjorb.data.User
import timber.log.Timber

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

        val main = activity as MainActivity
        main.showOrHideAppBar(false)

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
                val user = FirebaseAuth.getInstance().currentUser
                updateUser(user)
                val userId = user?.uid
                val action =
                    LoginFragmentDirections.actionLoginFragmentToRoutinesFragment(userId)
                this.findNavController().navigate(action)
            } else {
                if (response == null)
                //TODO: user cancelled, decide what to do
                else {
                    val error = response.error
                    Timber.d(error)
                    //TODO handle this error
                }
            }
        }
    }

    private fun updateUser(firebaseUser: FirebaseUser?) {
        val userId = firebaseUser?.uid ?: ""
        val user = User(firebaseUser?.displayName, userId)
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId).set(user)
    }
}
