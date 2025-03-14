package com.github.sample

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.sample.controller.CUSTOMER_BASE_ENDPOINT
import com.github.sample.dto.CustomerDto
import com.github.sample.dto.CustomerInputDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import java.util.UUID
import kotlin.test.assertNotNull

/**
 * Test class for testing the CustomerController.
 * This class extends the [BaseMockMvc] class so this will spin up a Spring Boot instance for the tests.
 * @param mockMvc The MockMvc instance to use for testing.
 */
class CustomerControllerTest(
    @Autowired mockMvc: MockMvc,
) : BaseMockMvc(mockMvc) {

    private val objectMapper = ObjectMapper().registerKotlinModule()

    @Test
    fun `get customer - random id - returns not found`() {
        val id = UUID.randomUUID()
        val response = mockGet("$CUSTOMER_BASE_ENDPOINT/$id")
        assertEquals(HttpStatus.NOT_FOUND.value(), response.status)
    }

    @Test
    fun `post customer - get customer - success`() {
        val customer = CustomerInputDto(mail = "bob@gmail.com")
        val json = objectMapper.writeValueAsString(customer)
        val response = mockPost(json, CUSTOMER_BASE_ENDPOINT)
        assertEquals(HttpStatus.CREATED.value(), response.status)

        val responseJson = response.contentAsString
        val createdCustomer = objectMapper.readValue(responseJson, CustomerDto::class.java)
        assertCustomer(createdCustomer, "bob@gmail.com")

        val response2 = mockGet("$CUSTOMER_BASE_ENDPOINT/${createdCustomer.id}")
        assertEquals(HttpStatus.OK.value(), response2.status)

        val responseJson2 = response2.contentAsString
        val fetchedCustomer = objectMapper.readValue(responseJson2, CustomerDto::class.java)
        assertCustomer(fetchedCustomer, "bob@gmail.com")
    }

    /**
     * Ensure that customer is not null and that the id is not null.
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
