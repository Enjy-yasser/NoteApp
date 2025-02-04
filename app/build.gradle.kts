plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
//    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
    id("org.jetbrains.kotlin.plugin.compose")
    id ("dagger.hilt.android.plugin")

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
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"

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
            kapt {
                correctErrorTypes= true
            }

        }
        dependencies {
            implementation(libs.androidx.core.ktx)

            implementation("androidx.appcompat:appcompat:1.7.0")

            implementation(platform("androidx.compose:compose-bom:2024.11.00"))
            implementation("androidx.activity:activity-compose:1.9.3")
            implementation("androidx.compose.material3:material3")
            implementation("androidx.compose.ui:ui")
            implementation("androidx.compose.ui:ui-tooling")
            implementation("androidx.compose.ui:ui-tooling-preview")
            implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
            implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
            implementation("androidx.navigation:navigation-compose:2.8.5")

            //Room
            implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
             kapt("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
//            implementation ("androidx.room:room-runtime:<latest_version>")
//            kapt ("androidx.room:room-compiler:<latest_version>")
            implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")

            // Hilt
            implementation ("com.google.dagger:hilt-android:2.51.1")
            kapt ("com.google.dagger:hilt-compiler:2.51.1")
        //    ksp ("com.google.dagger:hilt-compiler:2.41")
            implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

            // Testing
            testImplementation ("junit:junit:4.13.2")
            androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
            androidTestImplementation("androidx.test.ext:junit:1.2.1")
            androidTestImplementation ("androidx.test:runner:1.5.2")

        }
    }
}
