

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}



android {
    namespace = "com.example.tictactoe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tictactoe"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        dataBinding = true
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

    val version_coroutine = "1.3.7"
    val version_lifecycle_extensions = "2.2.0"
    val version_lifecycle = "2.2.0"
    val version_room = "2.2.5"
    // Room and Lifecycle dependencies
    implementation ("androidx.room:room-runtime:2.4.0-alpha03")
    annotationProcessor ("androidx.room:room-compiler:2.4.0-alpha03")
    kapt( "androidx.room:room-compiler:2.4.0-alpha03")
    implementation( "androidx.lifecycle:lifecycle-extensions:$version_lifecycle_extensions")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$version_coroutine")
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version_coroutine")

    // ViewModel and LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$version_lifecycle")

    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:$version_room")

    implementation("com.google.android.material:material:1.10.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation( "androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("androidx.constraintlayout:constraintlayout-core:1.0.4")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}