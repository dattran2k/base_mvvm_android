pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.google.com")
    }
}
rootProject.name = "Base MVVM Single Module"
include(":app")
include(":ui")
include(":core:datastore")
include(":core:model")
include(":core:data")
include(":core:designsystem")
include(":core:common")
include(":core:network")
