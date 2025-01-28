package com.example.noteapp

import android.app.Application
//import com.example.noteapp.ui.navigation.NavigationManager
//import com.example.noteapp.data.AppContainer
//import com.example.noteapp.data.AppDataContainer
import dagger.hilt.android.HiltAndroidApp
//import javax.inject.Inject

//n apply dagger hilt ..> Dependency injection

@HiltAndroidApp
 class NotesApplication : Application()
//{
//    @Inject
//    lateinit var navigationManager: NavigationManager
//    override fun onCreate() {
//        super.onCreate()
//    }
//}
//    lateinit var container: AppContainer
//
//    override fun onCreate() {
//        super.onCreate()
//        container = AppDataContainer(this)
//    }
//}