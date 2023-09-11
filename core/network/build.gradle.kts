import java.util.regex.Pattern

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.base.core.network"
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        when (getCurrentFlavor()) {
            "dev" -> {
                buildConfigField(
                    "String",
                    "BASE_URL",
                    "\"https://jsonplaceholder.typicode.com/\""
                )
            }

            "prod" -> {
                buildConfigField(
                    "String",
                    "BASE_URL",
                    "\"https://jsonplaceholder.typicode.com/\""
                )
            }
        }
    }
}
dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.dagger.hilt.library)
    ksp(libs.dagger.hilt.compiler)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.release)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
}

fun getCurrentFlavor(): String {
    val tskReqStr = gradle.startParameter.taskRequests.toString()

    val pattern = if (tskReqStr.contains("assemble"))
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    else
        Pattern.compile("generate(\\w+)(Release|Debug)")

    val matcher = pattern.matcher(tskReqStr)

    return if (matcher.find())
        matcher.group(1).lowercase()
    else {
        println("NO MATCH FOUND")
        ""
    }
}