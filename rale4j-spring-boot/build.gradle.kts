group = "com.rale4j"
version = "1.0.0"


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

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

plugins {
    `java-library`
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

sourceSets {
    create("intTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val intTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())


dependencies {
    // Core Dependencies
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-webflux") // WebFlux for WebSockets
    implementation("org.springframework.boot:spring-boot-starter-graphql") // GraphQL Support
    implementation("io.grpc:grpc-spring-boot-starter:2.14.0") // gRPC Support
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8") // Caching
    implementation("com.google.guava:guava:32.1.2-jre") // Guava Rate Limiting
    implementation("io.github.bucket4j:bucket4j-core:8.4.0") // Bucket4j Support
    implementation("io.github.resilience4j:resilience4j-ratelimiter:2.2.0") // Resilience4j

    // Redis for Rate Limiting
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("io.lettuce.core:lettuce-core:6.2.6.RELEASE") // Redis Client

    // Security Integrations
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("org.keycloak:keycloak-spring-boot-starter:23.0.1") // OAuth2 & JWT

    // Observability & Monitoring
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Testing Dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("org.assertj:assertj-core:3.25.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter:1.19.4")
    testImplementation("org.testcontainers:redis:1.19.4") // Redis testcontainer
    testImplementation("org.mockito:mockito-core:5.8.0")

    // Integration Testing
    intTestImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    intTestImplementation("org.assertj:assertj-core:3.25.1")
    intTestImplementation("org.testcontainers:junit-jupiter:1.19.4")
    intTestImplementation("org.testcontainers:redis:1.19.4")
}

val intTest = task<Test>("intTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = sourceSets["intTest"].output.classesDirs
    classpath = sourceSets["intTest"].runtimeClasspath
    shouldRunAfter("test")

    useJUnitPlatform()

    testLogging {
        events("passed")
    }
}

tasks.check { dependsOn(intTest) }

tasks.named<Test>("test") {
    useJUnitPlatform()
}

signing {
    val signingKey = providers.environmentVariable("GPG_SIGNING_KEY")
    val signingPassphrase = providers.environmentVariable("GPG_SIGNING_PASSPHRASE")
    if (signingKey.isPresent && signingPassphrase.isPresent) {
        useInMemoryPgpKeys(signingKey.get(), signingPassphrase.get())
        val extension = extensions.getByName("publishing") as PublishingExtension
        sign(extension.publications)
    }
}

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
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
            ),
        )
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

// Dependency Locking for Security (Trivy Scan)
dependencyLocking {
    lockAllConfigurations()
}

// Ensure parent task runs all subproject tasks
rootProject.tasks.dependencies { dependsOn(tasks.dependencies) }
