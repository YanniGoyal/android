package com.test.fragment

import android.app.Application

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPref.init(this)
    }
}