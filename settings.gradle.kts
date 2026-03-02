pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.florianreuth.de/releases")
    }

    plugins {
        id("de.florianreuth.baseproject") version "2.0.1"
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "dietrichevents2"
