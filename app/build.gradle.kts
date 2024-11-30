plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hilt) // Add this line
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.prueba1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.prueba1"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.adaptive.android)
    implementation(libs.androidx.appcompat)
    //implementation(libs.androidx.navigation.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("io.coil-kt:coil-compose:2.4.0")

    //
    //-----------------------
    // Work Manager
    implementation("androidx.work:work-runtime:2.9.0")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Dagger-hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    kapt("com.google.dagger:hilt-compiler:2.44.2") // Fix typo in your compiler dependency
    implementation("androidx.hilt:hilt-work:1.2.0")
    //--------------------------------

    // ------------------------------
    //Location
    //Mapas
    implementation("com.google.maps.android:maps-compose:2.14.0")
    implementation("com.google.android.gms:play-services-maps:19.0.0")
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //Places
    implementation("com.google.android.libraries.places:places:4.0.0")
    // ------------------------------

    // ---------------------------------
    // Biometrics
    implementation("androidx.biometric:biometric:1.1.0")
    //----------------------------------

    //Camera and files
    //implementation("io.coil-kt:coil-compose:2.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    //SQLITE
    val room_version = "2.6.1"
    ksp("androidx.room:room-compiler:$room_version")

    //INTERNET
    ////////////////////////////////////////////////
    implementation("androidx.compose.ui:ui:1.7.5")
    implementation("androidx.compose.material:material:1.7.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.5")
    implementation("androidx.activity:activity-compose:1.9.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.5")
    //implementation("io.coil-kt:coil-compose:2.4.0")

    //API
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    //implementation("com.squareup.retrofit2:retrofit:2.9.0") //Está en WorkManager
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    //implementation("io.coil-kt:coil-compose:2.4.0") //Está en BioMe
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    //implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Está en WorkManager
}