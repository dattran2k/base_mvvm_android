import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import java.util.Calendar
import java.text.SimpleDateFormat

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.cacheFixPlugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.gms.googleServices)
//    id("com.google.protobuf") version "0.9.1"
//    alias(libs.plugins.android.dagger.hilt.library)
    alias(libs.plugins.android.dagger.hilt)
    id("kotlin-parcelize")
    kotlin("kapt")
}
fun getDate(): String {
    val format = "HH\'h\')_\'Ngay\'_dd"
    val current = Calendar.getInstance().time
    return SimpleDateFormat(format).format(current)
}
android {

    namespace = "com.base"
    defaultConfig {
        applicationId  = "com.base"
        versionCode =1
        versionName ="1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        debug {

        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        viewBinding =  true
    }
    packaging {
        packagingOptions.resources.excludes += setOf(
            // Exclude AndroidX version files
            "META-INF/*.version",
            // Exclude consumer proguard files
            "META-INF/proguard/*",
            // Exclude the Firebase/Fabric/other random properties files
            "/*.properties",
            "fabric/*.properties",
            "META-INF/*.properties",
        )
    }

    flavorDimensions += "environment"
    productFlavors {
        create("development") {
            dimension = "environment"
            manifestPlaceholders["appLabel"] = "Base Debug"
            versionNameSuffix = "-development"

            buildConfigField("String", "BASE_URL", "\"https://demo.com\"")

        }
        create("production") {
            dimension = "environment"
            manifestPlaceholders["appLabel"] = "Base"
            versionNameSuffix = "-production"
            buildConfigField("String", "BASE_URL", "\"https://demo.com/\"")
        }
    }

}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)

    implementation(libs.androidx.activity.activity)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.dynamic.links)
    // hilt
    implementation(libs.dagger.hilt.library)
    kapt(libs.dagger.hilt.compiler)

    // dimension
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    // api
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
    // Timber
    implementation(libs.timber)
    // chucker
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.release)
    //Glide
    implementation(libs.glide)
}
