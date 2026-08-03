pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.florianreuth.de/releases")
    }

    plugins {
        id("me.champeau.jmh") version "0.7.3"
        id("de.florianreuth.baseproject") version "3.0.0"
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "dietrichevents2"
