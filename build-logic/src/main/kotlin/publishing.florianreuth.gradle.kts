// https://github.com/florianreuth/BaseProject

import java.net.HttpURLConnection
import java.net.URI
import java.util.Base64

plugins {
    id("base.maven_publish")
}

val reposiliteUsername = findProperty("reposiliteUsername") as String?
val reposilitePassword = findProperty("reposilitePassword") as String?
if (reposiliteUsername != null && reposilitePassword != null) {
    publishing {
        repositories {
            maven {
                name = "reposilite"
                url = uri(
                    "https://maven.florianreuth.de/" +
                        if (project.version.toString().contains("SNAPSHOT")) "snapshots" else "releases"
                )
                credentials {
                    username = reposiliteUsername
                    password = reposilitePassword
                }
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }
}

val sonatypeToken = findProperty("sonatypeToken") as String?
val sonatypePassword = findProperty("sonatypePassword") as String?
if (sonatypeToken != null && sonatypePassword != null) {
    val snapshot = project.version.toString().contains("SNAPSHOT")

    publishing {
        repositories {
            maven {
                name = "ossrh"
                url = uri(
                    if (snapshot) "https://central.sonatype.com/repository/maven-snapshots"
                    else "https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2"
                )
                credentials {
                    username = sonatypeToken
                    password = sonatypePassword
                }
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }

    if (!snapshot) {
        tasks.withType<PublishToMavenRepository>().matching {
            it.name.endsWith("ToOssrhRepository")
        }.configureEach {
            // Locals only: referencing script properties from doLast would capture the script for the configuration cache
            val closeUrl = "https://ossrh-staging-api.central.sonatype.com/manual/upload/defaultRepository/${project.group}"
            val encodedAuth = Base64.getEncoder().encodeToString("$sonatypeToken:$sonatypePassword".toByteArray())
            doLast("closeOssrhRepository") {
                val connection = (URI(closeUrl).toURL().openConnection() as HttpURLConnection).apply {
                    requestMethod = "POST"
                    setRequestProperty("Authorization", "Basic $encodedAuth")
                }

                if (connection.responseCode != 200) {
                    throw GradleException(
                        "Failed to close staging repository: ${connection.responseCode} ${connection.responseMessage}"
                    )
                }

                connection.disconnect()
            }
        }
    }
}
