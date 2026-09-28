plugins {
    `maven-publish`
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
