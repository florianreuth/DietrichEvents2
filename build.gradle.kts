plugins {
    `java-library`
    alias(libs.plugins.jmh)
    id("base.java")
    id("base.maven_publish")
    id("publishing.reposilite")
    id("publishing.maven_central")
    id("base.junit")
}

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)

    jmh(libs.jmh.core)
    jmh(libs.jmh.generator.annprocess)
    jmhAnnotationProcessor(libs.jmh.generator.annprocess)
}
