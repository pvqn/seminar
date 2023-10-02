package com.example.seminar.lifecycle_observer

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class Observer : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        Log.d("Main", "On create: Observer");
        super.onCreate(owner);
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d("Main", "On start: Observer");
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d("Main", "On resume: Observer");
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d("Main", "On pause: Observer");
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d("Main", "On stop: Observer");
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d("Main", "On destroy: Observer");
        super.onDestroy(owner)
    }
}