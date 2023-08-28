plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.cacheFixPlugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.dagger.hilt)
    alias(libs.plugins.protobuf)
    kotlin("kapt")
}

android {
    namespace = "com.base.core.datastore"

    defaultConfig {
        consumerProguardFiles("consumer-consumer-proguard-rules.pro")
    }
}
// Setup protobuf configuration, generating lite Java and Kotlin classes
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
    implementation(project(":core:model"))
    implementation(project(":core:model"))
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.dagger.hilt.library)
    kapt(libs.dagger.hilt.compiler)
}