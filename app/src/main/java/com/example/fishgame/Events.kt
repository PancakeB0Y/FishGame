package com.example.fishgame

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class Events : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
    }

    fun closeEvents(view: View) {
        finish()
    }
}