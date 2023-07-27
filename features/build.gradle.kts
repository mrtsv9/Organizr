plugins {
    id("com.android.library")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.8.20-1.0.10"
}

android {
    namespace = "com.mrtsv9.features"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
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

    implementation("androidx.core:core-ktx:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Base
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.material3:material3-android:1.2.0-alpha03")
    implementation("androidx.compose.material3:material3:1.2.0-alpha03")

    // FlowMVI
    val flowMVIVersion = "1.2.0-alpha01"
//    implementation("pro.respawn.flowmvi:core:$flowMVIVersion")
    implementation("pro.respawn.flowmvi:android-compose:$flowMVIVersion")
    implementation("pro.respawn.flowmvi:android:$flowMVIVersion")

//    // Orbit
//    val orbitVersion = "6.0.0"
//    implementation("org.orbit-mvi:orbit-core:$orbitVersion")
//    implementation("org.orbit-mvi:orbit-compose:$orbitVersion")

    // WorkManager
    val workVersion = "2.8.1"
    implementation("androidx.work:work-runtime-ktx:$workVersion")

    // Koin Compose
//    implementation("io.insert-koin:koin-androidx-navigation:3.2.0")
    implementation("io.insert-koin:koin-androidx-compose:3.4.5")
    implementation("io.insert-koin:koin-android:3.4.2")
    implementation("io.insert-koin:koin-core:3.4.2")

    // Kotlin Datetime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
}