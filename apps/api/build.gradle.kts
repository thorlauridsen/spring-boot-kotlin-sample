plugins {
	kotlin("plugin.spring") version local.versions.kotlin
	alias(local.plugins.springboot)
	alias(local.plugins.spring.dependencies)
}

dependencies {
	// The api subproject needs access to both the model and persistence subproject
	implementation(projects.model)
	implementation(projects.persistence)

	// Spring Boot dependencies
	implementation(local.springboot.starter)
	implementation(local.springboot.starter.validation)
	implementation(local.springboot.starter.webmvc)

    // Spring Boot Liquibase dependency for database migrations
    implementation(local.springboot.starter.liquibase)

	// Springdoc for swagger docs supporting Spring Web MVC
	implementation(local.springdoc.openapi.starter.webmvc)

	// H2 in-memory database
	runtimeOnly(local.h2database)

	// PostgreSQL database driver
	runtimeOnly(local.postgres)

	// Test dependencies
    testImplementation(local.springboot.resttestclient)
	testImplementation(local.springboot.starter.test)
	testImplementation(local.springboot.testcontainers)
    testImplementation(local.testcontainers.junit.jupiter)
	testImplementation(local.testcontainers.postgresql)
	testImplementation(local.kotlin.test.junit5)
	testRuntimeOnly(local.junit.platform.launcher)
}

tasks.test {
	useJUnitPlatform()
}
