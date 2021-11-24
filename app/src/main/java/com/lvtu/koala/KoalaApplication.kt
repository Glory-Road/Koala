package com.lvtu.koala

import android.app.Application
import com.tencent.mmkv.MMKV

class KoalaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        MMKV.initialize(this)
    }

    companion object{
        lateinit var INSTANCE: KoalaApplication
    }
}