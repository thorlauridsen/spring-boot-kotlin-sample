plugins {
	kotlin("plugin.spring") version local.versions.kotlinVersion
	alias(local.plugins.spring.boot)
	alias(local.plugins.spring.dependencies)
}

dependencies {
	implementation(projects.model)

	implementation(local.spring.boot.starter)
	implementation(local.spring.boot.starter.web)

	testImplementation(local.spring.boot.starter.test)
	testImplementation(local.kotlin.test.junit5)
	testRuntimeOnly(local.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
