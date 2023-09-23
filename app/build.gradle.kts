
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")

}

baseConfig()

compose()

android {
    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")

    androidBase()
    compose()

    room()
    annotationProcessor("androidx.room:room-compiler:2.4.3")

    dataStore()
    firebase()

    implementation(platform("com.google.firebase:firebase-bom:31.3.0"))
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.4")

    jackson()

    // ImageLoad
    implementation("com.github.skydoves:landscapist-glide:2.1.9")
    implementation("com.github.skydoves:landscapist-placeholder:2.1.9")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")

}