package com.github.thorlauridsen.service

import com.github.thorlauridsen.Customer
import com.github.thorlauridsen.CustomerInput
import com.github.thorlauridsen.CustomerRepo
import com.github.thorlauridsen.exception.CustomerNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * This service is responsible for:
 * - Saving customers.
 * - Fetching customers.
 *
 * @param customerRepo Exposed [CustomerRepo] to interact with the database.
 */
@Service
class CustomerService(private val customerRepo: CustomerRepo) {

    private val logger = LoggerFactory.getLogger(CustomerService::class.java)

    /**
     * Save a customer.
     * @param customer [CustomerInput] to save.
     * @return [Customer] retrieved from database.
     */
    fun save(customer: CustomerInput): Customer {
        logger.info("Saving customer to database: $customer")

        return customerRepo.save(customer)
    }

    /**
     * Get a customer given an id.
     * @param id [UUID] to fetch customer.
     * @throws CustomerNotFoundException if no customer found with given id.
     * @return [Customer] or null if not found.
     */
    fun find(id: UUID): Customer {
        logger.info("Retrieving customer with id: $id")

        val customer = customerRepo.find(id)
            ?: throw CustomerNotFoundException(id)

        logger.info("Found customer: $customer")
        return customer
    }
}
