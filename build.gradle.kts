plugins {
    // Use the Kotlin JVM plugin
    kotlin("jvm") version "1.8.20"
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Test (including JUnit 5 support)
    testImplementation(kotlin("test"))
    // Explicitly add JUnit Jupiter if needed
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

kotlin{
    jvmToolchain(17)
}

tasks.withType<Test> {
    // Enable JUnit 5
    useJUnitPlatform()
}

// Hide Kotlin parameter/variable warnings like “never used”
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-nowarn")
    }
}
