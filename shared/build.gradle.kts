plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-atomicfu")
    kotlin("plugin.serialization") version "1.8.21"
    id("app.cash.sqldelight") version "2.0.0-rc02"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    sqldelight {
        databases {
            create("Database") {
                packageName.set("com.mrtsv9")
            }
        }
    }

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")

                // WorkManager
                val workVersion = "2.8.1"
                implementation("androidx.work:work-runtime-ktx:$workVersion")

                // FlowMVI? mb isn't need to be here
                val flowMVIVersion = "1.2.0-alpha01"
                implementation("pro.respawn.flowmvi:core:$flowMVIVersion")

                // Koin
                val koinVersion = "3.4.2"
                implementation("io.insert-koin:koin-core:$koinVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:android-driver:2.0.0-rc02")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:native-driver:2.0.0-rc02")
            }
        }
    }
}

android {
    namespace = "com.mrtsv9.organizr"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}