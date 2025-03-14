import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    kotlin("plugin.spring") version local.versions.kotlinVersion
    alias(local.plugins.spring.boot)
    alias(local.plugins.spring.dependencies)
}

dependencies {
    // Persistence project needs to know about the model project
    implementation(projects.model)

    // JetBrains Exposed - Kotlin SQL library
    implementation(local.exposed.spring.boot.starter)
}

// Disabling bootJar and bootRun is necessary for a subproject/module
// that uses the Spring Boot plugin but is not supposed to be executable.
tasks.named<BootJar>("bootJar") {
    enabled = false
}
tasks.named<BootRun>("bootRun") {
    enabled = false
}
