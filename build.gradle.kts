plugins {
	kotlin("jvm") version local.versions.kotlinVersion
	kotlin("plugin.spring") version local.versions.kotlinVersion
	alias(local.plugins.spring.boot)
	alias(local.plugins.spring.dependencies)
}

group = "com.github"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation(local.spring.boot.starter)
	testImplementation(local.spring.boot.starter.test)
	testImplementation(local.kotlin.test.junit5)
	testRuntimeOnly(local.junit.platform.launcher)
}

kotlin {
	jvmToolchain(21)
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
