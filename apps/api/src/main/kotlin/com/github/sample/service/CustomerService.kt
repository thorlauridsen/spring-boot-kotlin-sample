package com.github.sample.service

import com.github.sample.Customer
import com.github.sample.CustomerInput
import com.github.sample.CustomerRepo
import com.github.sample.exception.CustomerNotFoundException
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
    fun saveCustomer(customer: CustomerInput): Customer {
        logger.info("Saving customer to database: $customer")

        return customerRepo.saveCustomer(customer)
    }

    /**
     * Get a customer given an id.
     * @param id [UUID] to fetch customer.
     * @return [Customer] or null if not found.
     */
    fun getCustomer(id: UUID): Customer {
        logger.info("Retrieving customer with id: $id")

        val customer = customerRepo.getCustomer(id)
            ?: throw CustomerNotFoundException(id)

        logger.info("Found customer: $customer")
        return customer
    }
}
