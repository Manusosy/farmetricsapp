plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "org.farmetricsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.farmetricsapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        // Mapbox configuration
        manifestPlaceholders["MAPBOX_ACCESS_TOKEN"] = project.property("MAPBOX_ACCESS_TOKEN") as String? ?: ""
        
        // Supabase configuration
        buildConfigField("String", "SUPABASE_URL", "\"${project.property("SUPABASE_URL") as String? ?: ""}\"")
        buildConfigField("String", "SUPABASE_ANON_KEY", "\"${project.property("SUPABASE_ANON_KEY") as String? ?: ""}\"")
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
    
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:${libs.versions.hilt.get()}")
    kapt("com.google.dagger:hilt-android-compiler:${libs.versions.hilt.get()}")
    implementation("androidx.hilt:hilt-work:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Room
    implementation("androidx.room:room-runtime:${libs.versions.room.get()}")
    implementation("androidx.room:room-ktx:${libs.versions.room.get()}")
    kapt("androidx.room:room-compiler:${libs.versions.room.get()}")

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:${libs.versions.retrofit.get()}")
    implementation("com.squareup.retrofit2:converter-gson:${libs.versions.retrofit.get()}")
    implementation("com.squareup.okhttp3:okhttp:${libs.versions.okhttp.get()}")
    implementation("com.squareup.okhttp3:logging-interceptor:${libs.versions.okhttp.get()}")

    // Supabase
    implementation("io.github.jan-tennert.supabase:gotrue-kt:${libs.versions.supabase.get()}")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:${libs.versions.supabase.get()}")
    implementation("io.github.jan-tennert.supabase:storage-kt:${libs.versions.supabase.get()}")

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:${libs.versions.workManager.get()}")

    // Mapbox
    implementation("com.mapbox.maps:android:${libs.versions.mapbox.get()}")

    // CameraX
    implementation("androidx.camera:camera-camera2:${libs.versions.camerax.get()}")
    implementation("androidx.camera:camera-lifecycle:${libs.versions.camerax.get()}")
    implementation("androidx.camera:camera-view:${libs.versions.camerax.get()}")

    // Coil
    implementation("io.coil-kt:coil-compose:${libs.versions.coil.get()}")

    // Accompanist
    implementation("com.google.accompanist:accompanist-permissions:${libs.versions.accompanist.get()}")
}