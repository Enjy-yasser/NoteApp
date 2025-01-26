plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
    id("org.jetbrains.kotlin.plugin.compose")
}
android {
    namespace = "com.example.noteapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.noteapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}
dependencies {
    implementation ("androidx.core:core-ktx:1.15.0")

    implementation ("androidx.appcompat:appcompat:1.7.0")

    implementation(platform("androidx.compose:compose-bom:2024.11.00"))
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.navigation:navigation-compose:2.8.4")

    //Room
    implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
    ksp("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
    implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")

    // Hilt
//    implementation ("com.google.dagger:hilt-android:2.41")
//    ksp ("com.google.dagger:hilt-compiler:2.41")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Testing
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")

}
//plugins {
//    id ("com.android.application")
//    id ("org.jetbrains.kotlin.android")
//    id ("kotlin-kapt")
//    id ("dagger.hilt.android.plugin")
//    id ("kotlin-parcelize")
//}
//
//android {
//    namespace =  "com.example.note_app"
//    compileSdk =  33
//
//    defaultConfig {
//        applicationId = "com.example.note_app"
//        minSdk =  26
//        targetSdk = 33
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary =true
//        }
//    }
//
//    buildTypes {
//        release {
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//                targetCompatibility= JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    buildFeatures {
//        compose =true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion= "1.2.0"
//
//    }
//}
//
//dependencies {
//
//    implementation ("androidx.core:core-ktx:1.7.0")
//    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
//    implementation ("androidx.activity:activity-compose:1.3.1")
//    implementation ("androidx.compose.ui:ui:1.2.0")
//    implementation ("androidx.compose.ui:ui-tooling-preview:1.2.0")
//    implementation ("androidx.compose.material:material:1.2.0")
//    testImplementation ("junit:junit:4.13.2")
//    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.2.0")
//    debugImplementation ("androidx.compose.ui:ui-tooling:1.2.0")
//    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.2.0")
//
//
//    //navigation
//    implementation("androidx.navigation:navigation-compose:2.5.3")
//    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.2-alpha")
//
//    //Icons"
//    implementation ("androidx.compose.material:material-icons-extended:1.2.0")
//
//    //coil image library
//    implementation("io.coil-kt:coil-compose:2.4.0")
//
//    //view model
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
//
//    //Hilt-Dagger
//    implementation ("com.google.dagger:hilt-android:2.44")
//    kapt ("com.google.dagger:hilt-compiler:2.44")
//
//    //Room
//    implementation ("androidx.room:room-runtime:2.5.2")
//    annotationProcessor ("androidx.room:room-compiler:2.5.2")
//
//    // To use Kotlin annotation processing tool (kapt) MUST HAVE!
//    kapt("androidx.room:room-compiler:2.5.2")
//    implementation ("androidx.room:room-ktx:2.5.2")
//
//
//
//
//    // Coroutines
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.1")
//
//    //gson
//    implementation ("com.google.code.gson:gson:2.10.1")
//}
