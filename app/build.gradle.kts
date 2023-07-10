plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.8.20-1.0.10"
}

android {
    namespace = "com.mrtsv9.organizr.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.mrtsv9.organizr.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
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
    implementation(project(":shared"))

    // Base
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.1")

    // Coroutines-Android
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")

    // Compose Animation
    implementation("androidx.compose.animation:animation:1.4.3")

    // Compose Navigation
    val navigationVersion = "1.8.42-beta"
    implementation("io.github.raamcosta.compose-destinations:core:$navigationVersion")
    ksp("io.github.raamcosta.compose-destinations:ksp:$navigationVersion")

    // FlowMVI
    val flowMVIVersion = "1.2.0-alpha01"
    implementation("pro.respawn.flowmvi:android-compose:$flowMVIVersion")

    // Koin
    val koinAndroidComposeVersion = "3.4.5"
    implementation("io.insert-koin:koin-androidx-compose:$koinAndroidComposeVersion")

}