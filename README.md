
# Introduction

Rale4j (**Rate Limiting Enhancement for Java Devs**) is a powerful, lightweight, and configurable **rate-limiting** library for Spring Boot applications.

[![Maven Central](https://img.shields.io/maven-central/v/com.rale4j/rale4j-core.svg)](https://central.sonatype.com/artifact/com.rale4j/rale4j-core)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://rale4j.github.io/rale4j-spring-boot/javadoc/)

## 🔥 Why Use Rale4j?
✅ **Simple Annotations** – Just use `@Rale4j` to control API access.  
✅ **Flexible Configuration** – Customize limits via `application.properties` or Java configurations.  
✅ **In-Memory & Redis Support** – Choose between **Guava Cache** (in-memory) or **Redis** for distributed rate limiting.  
✅ **High Performance** – Uses efficient algorithms like **Token Bucket, Sliding Window, and Leaky Bucket**.  
✅ **Observability** – Integrates with **Prometheus & Grafana** for monitoring.  

## 📦 Installation

### **Maven**
Add this dependency to your `pom.xml`:
```xml
<dependency>
    <groupId>com.rale4j</groupId>
    <artifactId>rale4j-core</artifactId>
    <version>1.0.0</version>
</dependency>
```
Gradle (Kotlin DSL)
```kts
implementation("com.rale4j:rale4j-core:1.0.0")
```
Gradle (Groovy DSL)
```groovy
implementation group: 'com.rale4j', name: 'rale4j-core', version: '1.0.0'
```
## ⚙️ Getting Started

- Install Rale4j using the dependencies above.
- Configure Rate Limits in application.properties (or application.yml).
- Use the `@Rale4j` Annotation to enforce limits.
# Features
- To publish core Rate limiting enhancement plugin to maven central
- With Compatibility with maven
- Use Gradle with Kotlin DSL

## Credits
- Base CI/CD actions adapted from by [Kotlin Template](https://github.com/cortinico/kotlin-android-template)
- PR & issue templates adapted from [Nuxt](https://github.com/nuxt/nuxt)
- Maven ,Security scan and Java Docs templates adapted from [Java Library Template](https://github.com/thriving-dev/java-library-template)  


