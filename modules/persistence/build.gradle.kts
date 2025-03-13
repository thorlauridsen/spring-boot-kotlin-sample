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
