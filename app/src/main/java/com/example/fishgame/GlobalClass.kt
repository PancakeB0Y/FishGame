package com.example.fishgame

import android.app.Application

class GlobalClass: Application() {
    companion object {
        const val FILE_NAME = "eventsData.txt"
    }
}