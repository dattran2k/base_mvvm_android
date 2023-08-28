plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.cacheFixPlugin)
}

android {
    namespace = "com.dat.core.common"
}
dependencies {

    implementation(project(":core:model"))
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.timber)
    implementation(libs.glide)
}