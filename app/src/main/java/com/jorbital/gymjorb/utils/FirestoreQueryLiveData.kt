package com.jorbital.gymjorb.utils

import android.os.Handler
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import timber.log.Timber

class FirestoreQueryLiveData(private val query: Query) : LiveData<QuerySnapshot>() {
    private val listener = MyValueEventListener()
    private var listenerRegistration: ListenerRegistration? = null

    private var listenerRemovePending = false
    private val handler = Handler()

    private val removeListener = Runnable {
        listenerRegistration!!.remove()
        listenerRemovePending = false
    }

    override fun onActive() {
        super.onActive()

        Timber.d("onActive")
        if (listenerRemovePending) {
            handler.removeCallbacks(removeListener)
        } else {
            listenerRegistration = query.addSnapshotListener(listener)
        }
        listenerRemovePending = false
    }

    override fun onInactive() {
        super.onInactive()

        Timber.d("onInactive: ")
        // Listener removal is schedule on a two second delay
        handler.postDelayed(removeListener, 2000)
        listenerRemovePending = true
    }

    private inner class MyValueEventListener : EventListener<QuerySnapshot> {
        override fun onEvent(@Nullable querySnapshot: QuerySnapshot?, @Nullable e: FirebaseFirestoreException?) {
            if (e != null) {
                Timber.e(e, "Can't listen to query snapshots: %s", querySnapshot)
                return
            }
            value = querySnapshot
        }
    }
}