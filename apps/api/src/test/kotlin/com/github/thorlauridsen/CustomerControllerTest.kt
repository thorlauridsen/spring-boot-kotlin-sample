package com.github.thorlauridsen

import com.github.thorlauridsen.controller.CUSTOMER_BASE_ENDPOINT
import com.github.thorlauridsen.dto.CustomerDto
import com.github.thorlauridsen.dto.CustomerInputDto
import com.github.thorlauridsen.dto.ErrorDto
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.client.RestTestClient
import org.springframework.test.web.servlet.client.expectBody
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import tools.jackson.databind.json.JsonMapper

/**
 * Test class for testing the CustomerController.
 * This class extends the [BaseControllerTest] class so this will spin up a Spring Boot instance for the tests.
 * A local Docker instance is required to run the tests as Testcontainers is used.
 */
@ActiveProfiles("postgres")
@Testcontainers
class CustomerControllerTest(
    @Autowired private val jsonMapper: JsonMapper,
    @Autowired private val restTestClient: RestTestClient
) : BaseControllerTest(restTestClient) {

    companion object {
        @Container
        @ServiceConnection
        @Suppress("unused")
        val postgres = PostgreSQLContainer("postgres:18")
    }

    @Test
    fun `get customer - random id - returns not found`() {
        val id = UUID.randomUUID()
        val response = get("$CUSTOMER_BASE_ENDPOINT/$id")

        response.expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "alice@gmail.com",
            "bob@gmail.com",
        ]
    )
    fun `post customer - get customer - success`(mail: String) {
        val customer = CustomerInputDto(mail)
        val json = jsonMapper.writeValueAsString(customer)
        val response = post(CUSTOMER_BASE_ENDPOINT, json)
        response.expectStatus().isEqualTo(HttpStatus.CREATED)

        val createdCustomer = response.expectBody<CustomerDto>().returnResult().responseBody

        requireNotNull(createdCustomer)
        assertCustomer(createdCustomer, mail)

        val response2 = get("$CUSTOMER_BASE_ENDPOINT/${createdCustomer.id}")
        response2.expectStatus().isEqualTo(HttpStatus.OK)

        val fetchedCustomer = response2.expectBody<CustomerDto>().returnResult().responseBody

        requireNotNull(fetchedCustomer)
        assertCustomer(fetchedCustomer, mail)
    }

    @Test
    fun `post customer - blank email - returns bad request`() {
        val customer = CustomerInputDto("")
        val json = jsonMapper.writeValueAsString(customer)
        val response = post(CUSTOMER_BASE_ENDPOINT, json)
        response.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)

        val error = response.expectBody<ErrorDto>().returnResult().responseBody

        requireNotNull(error)
        assertEquals("Validation failed", error.description)
        assertTrue(error.fieldErrors.containsKey("mail"))
        assertEquals("Email is required", error.fieldErrors["mail"])
    }

    @Test
    fun `post customer - invalid email format - returns bad request`() {
        val customer = CustomerInputDto("invalid-email")
        val json = jsonMapper.writeValueAsString(customer)
        val response = post(CUSTOMER_BASE_ENDPOINT, json)
        response.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)

        val error = response.expectBody<ErrorDto>().returnResult().responseBody

        requireNotNull(error)
        assertEquals("Validation failed", error.description)
        assertTrue(error.fieldErrors.containsKey("mail"))
        assertEquals("Invalid email format", error.fieldErrors["mail"])
    }

    /**
     * Ensure that the customer is not null and that the id is not null.
     * Assert that the mail is equal to the expected mail.
     * @param customer [CustomerDto]
     * @param expectedMail Expected mail of the customer.
     */
    private fun assertCustomer(customer: CustomerDto, expectedMail: String) {
        assertNotNull(customer)
        assertNotNull(customer.id)
        assertEquals(expectedMail, customer.mail)
    }
}
