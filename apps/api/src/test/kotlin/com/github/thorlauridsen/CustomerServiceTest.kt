package com.github.thorlauridsen

import com.github.thorlauridsen.exception.CustomerNotFoundException
import com.github.thorlauridsen.service.CustomerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

/**
 * Test class for testing the [CustomerService].
 * This class uses the @SpringBootTest annotation to spin up a Spring Boot instance.
 * This ensures that Spring can automatically inject [CustomerService] with a [CustomerRepo]
 * @param customerService The [CustomerService] to test.
 */
@SpringBootTest
class CustomerServiceTest(
    @Autowired private val customerService: CustomerService,
) {

    @Test
    fun `get customer - random id - returns not found`() {
        val id = UUID.randomUUID()
        assertThrows<CustomerNotFoundException> {
            customerService.getCustomer(id)
        }
    }

    @Test
    fun `save customer - get customer - success`() {
        val customer = CustomerInput(mail = "bob@gmail.com")

        val savedCustomer = customerService.saveCustomer(customer)
        assertCustomer(savedCustomer, "bob@gmail.com")

        val fetchedCustomer = customerService.getCustomer(savedCustomer.id)
        assertCustomer(fetchedCustomer, "bob@gmail.com")
    }

    /**
     * Ensure that customer is not null and that the id is not null.
     * Assert that the mail is equal to the expected mail.
     * @param customer [Customer]
     * @param expectedMail Expected mail of the customer.
     */
    private fun assertCustomer(customer: Customer, expectedMail: String) {
        assertNotNull(customer)
        assertNotNull(customer.id)
        assertEquals(expectedMail, customer.mail)
    }
}
