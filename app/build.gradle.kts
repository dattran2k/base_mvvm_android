import java.text.SimpleDateFormat
import java.util.Calendar

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.cacheFixPlugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.gms.googleServices)
    alias(libs.plugins.android.dagger.hilt)
    id("kotlin-parcelize")
    id("kotlin-kapt")

}
fun getDate(): String {
    val format = "HH\'h\')_\'Day\'_dd"
    val current = Calendar.getInstance().time
    return SimpleDateFormat(format).format(current)
}
android {
    namespace = "com.base"
    defaultConfig {
        applicationId = "com.base"
        versionCode = 1
        versionName = "1.0"
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
    buildFeatures {
        buildConfig = true
        viewBinding = true
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

        }
        create("production") {
            dimension = "environment"
            manifestPlaceholders["appLabel"] = "Base"
            versionNameSuffix = "-production"
        }
    }
}
dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
    implementation(project(":ui"))
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.activity.activity)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.work.runtime)
    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.dynamic.links)
    // hilt
    implementation(libs.dagger.hilt.library)
    // TODO ksp currently not support hilt yet. Still use kapt
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.timber)

}