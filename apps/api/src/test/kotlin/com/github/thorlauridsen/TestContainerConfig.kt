package com.github.thorlauridsen

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

@TestConfiguration(proxyBeanMethods = false)
class TestContainerConfig {

    /**
     * A [PostgreSQLContainer] bean to be used in tests.
     * This uses Testcontainers to spin up a temporary PostgreSQL instance in a Docker container.
     * The [ServiceConnection] annotation allows Spring Boot to automatically
     * configure the datasource properties based on the container settings.
     */
    @Bean
    @ServiceConnection // lets Spring Boot wire spring.datasource.* automatically
    fun postgresContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer("postgres:17")
            .withDatabaseName("sample-db")
            .withUsername("postgres")
            .withPassword("postgres")
    }
}
