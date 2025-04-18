package com.github.thorlauridsen

import com.github.thorlauridsen.model.CustomerInput
import com.github.thorlauridsen.persistence.CustomerRepo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

/**
 * Test class for testing the [CustomerRepo].
 * This class uses the @SpringBootTest annotation to spin up a Spring Boot instance.
 * @param customerRepo The [CustomerRepo] to test.
 */
@SpringBootTest
class CustomerRepoTest(
    @Autowired private val customerRepo: CustomerRepo,
) {

    @Test
    fun `save customer - get customer - success`() {
        val mail = "test@example.com"
        val customer = CustomerInput(mail)

        val savedCustomer = customerRepo.save(customer)

        assertNotNull(savedCustomer)
        assertNotNull(savedCustomer.id)
        assertEquals(mail, savedCustomer.mail)

        val foundCustomer = customerRepo.find(savedCustomer.id)

        assertNotNull(foundCustomer)
        assertEquals(savedCustomer.id, foundCustomer?.id)
        assertEquals(mail, foundCustomer?.mail)
    }

    @Test
    fun `get customer - non-existent id - returns null`() {
        val id = UUID.randomUUID()
        val customer = customerRepo.find(id)
        assertNull(customer)
    }
}