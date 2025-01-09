plugins {
    alias(libs.plugins.android.application)
    //TODO: 2.3 Agregar plugin compose
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    //alias(libs.plugins.dagger.hilt)
   // alias(libs.plugins.room)
   // alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.comunicaciones"
    compileSdk = 34

    packaging {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }

    defaultConfig {
        applicationId = "com.example.comunicaciones"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //Librerias Android y compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.identity.jvm)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)


    //Librerias Room
    //implementation(libs.room.ktx)
    //implementation(libs.room.runtime)
    //ksp(libs.room.compiler)

    //Librerias Dagger Hilt
   // implementation(libs.dagger.hilt.navigation.compose)
    //implementation(libs.dagger.hilt)
   // ksp(libs.dagger.hilt.compiler)

    //Libreria Serializacion

    implementation(libs.kotlinx.serialization.json)


    //coli
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    //weas
    implementation ("com.auth0:java-jwt:4.2.1")
    implementation ("commons-codec:commons-codec:1.15")

}