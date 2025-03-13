plugins {
	kotlin("plugin.spring") version local.versions.kotlinVersion
	alias(local.plugins.spring.boot)
	alias(local.plugins.spring.dependencies)
}

dependencies {
	// The api project needs access to both the model and persistence project
	implementation(projects.model)
	implementation(projects.persistence)

	// Spring Boot dependencies
	implementation(local.spring.boot.starter)
	implementation(local.spring.boot.starter.web)

	// SpringDoc for swagger docs supporting Spring Web MVC
	implementation(local.springdoc.openapi.starter.webmvc)

	// Liquibase for database migrations
	implementation(local.liquibase.core)

	// H2 in-memory database
	implementation(local.h2database)

	// FasterXML Jackson module for Kotlin support
	implementation(local.jackson.module.kotlin)

	// Test dependencies
	testImplementation(local.spring.boot.starter.test)
	testImplementation(local.kotlin.test.junit5)
	testRuntimeOnly(local.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
