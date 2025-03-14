import org.gradle.api.tasks.testing.logging.TestLogEvent

group = "com.rale4j"
version = "1.0.0-SNAPSHOT" // Snapshot version

object Meta {
    const val release = "https://s01.oss.sonatype.org/service/local/"
    const val snapshot = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
    const val desc = "Rate Limiting Enhancement Plugin for Spring Boot"
    const val license = "Apache-2.0"
    const val licenseUrl = "https://opensource.org/licenses/Apache-2.0"
    const val githubRepo = "rale4j/rale4j-spring-boot"
    const val developerId = "jvlavan"
    const val developerName = "LAVAN J V"
    const val developerOrganization = "RALE4J"
    const val developerOrganizationUrl = "https://rale4j.com"
}

plugins {
    `java-library`
    `maven-publish`
    signing
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-aop:3.1.4") // AOP support
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter-security:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter-websocket:3.1.4") // WebSocket support

    // Rate Limiting Libraries
    implementation("com.google.guava:guava:32.1.2-jre") // Guava Rate Limiter
    implementation("io.github.resilience4j:resilience4j-ratelimiter:2.0.2") // Resilience4j
    implementation("com.bucket4j:bucket4j-core:8.10.1") // Bucket4j
    implementation("org.springframework.data:spring-data-redis:3.1.4") // Redis

    // Observability
    implementation("io.micrometer:micrometer-core:1.11.3") // Micrometer for metrics
    implementation("io.micrometer:micrometer-registry-prometheus:1.11.3") // Prometheus integration
    // Java Servlet API
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
    }
}

// Publishing to Sonatype Snapshots Repository
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])
            pom {
                name.set(project.name)
                description.set(Meta.desc)
                url.set("https://github.com/${Meta.githubRepo}")
                licenses {
                    license {
                        name.set(Meta.license)
                        url.set(Meta.licenseUrl)
                    }
                }
                developers {
                    developer {
                        id.set(Meta.developerId)
                        name.set(Meta.developerName)
                        organization.set(Meta.developerOrganization)
                        organizationUrl.set(Meta.developerOrganizationUrl)
                    }
                }
                scm {
                    url.set("https://github.com/${Meta.githubRepo}.git")
                    connection.set("scm:git:git://github.com/${Meta.githubRepo}.git")
                    developerConnection.set("scm:git:git://github.com/${Meta.githubRepo}.git")
                }
                issueManagement {
                    url.set("https://github.com/${Meta.githubRepo}/issues")
                }
            }
        }
    }
    repositories {
        maven {
            url = uri(Meta.snapshot) // Point to the Sonatype Snapshots repository
            credentials {
                username = providers.environmentVariable("OSSRH_USERNAME").orNull
                password = providers.environmentVariable("OSSRH_PASSWORD").orNull
            }
        }
    }
}

// Signing Artifacts (optional for snapshots)
signing {
    val signingKey = providers.environmentVariable("GPG_SIGNING_KEY").orNull
    val signingPassphrase = providers.environmentVariable("GPG_SIGNING_PASSPHRASE").orNull
    if (signingKey != null && signingPassphrase != null) {
        useInMemoryPgpKeys(signingKey, signingPassphrase)
        sign(publishing.publications["maven"])
    }
}