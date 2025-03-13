plugins {
    kotlin("plugin.spring") version local.versions.kotlinVersion
    alias(local.plugins.spring.boot)
    alias(local.plugins.spring.dependencies)
}

dependencies {
    implementation(projects.model)
    implementation(local.exposed.spring.boot.starter)
}
