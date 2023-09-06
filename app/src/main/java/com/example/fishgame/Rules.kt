package com.example.fishgame

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class Rules : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)
    }

    fun closeRules(view: View){
        finish()
    }
}