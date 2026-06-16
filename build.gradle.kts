plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "2.4.0"

    // Apply the application plugin to add support for building a CLI application.
    application
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Google Agent Development Kit (ADK) Core
    implementation("com.google.adk:google-adk-kotlin-core:0.3.0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Use JUnit Jupiter Engine for testing.
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    // Define the main class for the application.
    mainClass.set("com.google.adk.githubactions.discussion.AppKt")
}

tasks.test {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
