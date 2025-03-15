plugins {
	kotlin("jvm") version local.versions.kotlin
}

group = "com.github.thorlauridsen"
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
