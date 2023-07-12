plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
}

buildscript {
    repositories { mavenCentral() }
    dependencies {
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.20.1")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}