import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.BaseExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.dagger.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.navigation.args) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.google.com")
        maven("https://jitpack.io")
    }

    // Configure Android projects
    pluginManager.withPlugin("com.android.application") {
        configureAndroidProject()
    }
    pluginManager.withPlugin("com.android.library") {
        configureAndroidProject()
    }
    pluginManager.withPlugin("com.android.test") {
        configureAndroidProject()
    }
}

fun Project.configureAndroidProject() {
    extensions.configure<BaseExtension> {
        compileSdkVersion(libs.versions.compileSdk.get().toInt())
        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            targetSdk = libs.versions.targetSdk.get().toInt()
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_18
            targetCompatibility = JavaVersion.VERSION_18
        }
        if (this is LibraryExtension) {
            println("configureAndroidProject = $name LibraryExtension")
            configureFlavors(this)
        }
        if (this is ApplicationExtension) {
            configureFlavors(this)
        }
    }

}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        flavorDimensions += "development"
        productFlavors {
            Flavor.values().forEach {
                create(it.name) {
                    dimension = "development"
                    if (this@apply is ApplicationExtension && this is com.android.build.api.dsl.ApplicationProductFlavor) {
                        versionNameSuffix = it.versionNameSuffix
                    }
                }
            }
        }
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
@Suppress("EnumEntryName")
enum class Flavor(val versionNameSuffix: String, val appLabel: String) {
    dev("-development", "Base Debug"),
    prod("-production", "Base")
}