
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("room_version", "2.6.1")
    }
}

plugins {
    id("com.android.application") version "8.7.3" apply false
    id("com.android.library") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
    id ("com.google.dagger.hilt.android") version "2.51.1" apply false

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

//buildscript {
//
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath ("com.android.tools.build:gradle:8.7.0")
//        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
//    }
//}// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id ("com.android.application") version "8.7.0" apply false
//    id ("com.android.library" )version "8.7.0" apply false
//    id ("org.jetbrains.kotlin.android") version "1.7.0" apply false
//}