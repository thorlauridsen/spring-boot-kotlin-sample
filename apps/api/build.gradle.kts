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
	implementation(local.springboot.starter.web)
	implementation(local.springboot.starter.validation)

	// Springdoc for swagger docs supporting Spring Web MVC
	implementation(local.springdoc.openapi.starter.webmvc)

	// Liquibase for database migrations
	runtimeOnly(local.liquibase.core)

	// H2 in-memory database
	runtimeOnly(local.h2database)

	// PostgreSQL database driver
	runtimeOnly(local.postgres)

	// FasterXML Jackson module for Kotlin support
	implementation(local.bundles.jackson)

	// Test dependencies
	testImplementation(local.springboot.starter.test)
	testImplementation(local.kotlin.test.junit5)
	testRuntimeOnly(local.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
