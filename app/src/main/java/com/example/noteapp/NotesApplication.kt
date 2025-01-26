package com.example.noteapp

import android.app.Application
import com.example.noteapp.data.AppContainer
import com.example.noteapp.data.AppDataContainer
//import dagger.hilt.android.HiltAndroidApp

//n apply dagger hilt ..> Dependency injection

//@HiltAndroidApp

 class NotesApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}