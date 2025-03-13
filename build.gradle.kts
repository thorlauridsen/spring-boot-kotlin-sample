plugins {
	kotlin("jvm") version local.versions.kotlinVersion
	kotlin("plugin.spring") version local.versions.kotlinVersion apply false
	alias(local.plugins.spring.boot) apply false
	alias(local.plugins.spring.dependencies) apply false
}

group = "com.github"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

kotlin {
	jvmToolchain(21)
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
}
