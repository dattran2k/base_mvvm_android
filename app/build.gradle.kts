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
    alias(libs.plugins.navigation.args)
    alias(libs.plugins.android.dagger.hilt)
    alias(libs.plugins.protobuf)
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
    sourceSets {
        named("main") {
            res.srcDirs(
                "src/main/res/layouts",
                "src/main/res/layouts/common",
                "src/main/res/layouts/screen",
                "src/main/res/layouts/adapter_item"
            )
        }
    }
    flavorDimensions += "environment"
    productFlavors {
        create("development") {
            dimension = "environment"
            manifestPlaceholders["appLabel"] = "Base Debug"
            versionNameSuffix = "-development"

            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")

        }
        create("production") {
            dimension = "environment"
            manifestPlaceholders["appLabel"] = "Base"
            versionNameSuffix = "-production"
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
        }
    }

}
protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}
dependencies {

//    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.androidx.activity.activity)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.work.runtime)
    //data store
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.kotlin.lite)
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

    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)

    implementation(libs.timber)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.release)


}