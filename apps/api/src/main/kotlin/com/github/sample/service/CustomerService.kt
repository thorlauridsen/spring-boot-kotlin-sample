package com.github.sample.service

import com.github.sample.Customer
import com.github.sample.CustomerInput
import com.github.sample.CustomerRepo
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

    /**
     * Save a customer.
     * @param customer [CustomerInput] to save.
     * @return [Customer] retrieved from database.
     */
    fun saveCustomer(customer: CustomerInput): Customer {
        return customerRepo.saveCustomer(customer)
    }

    /**
     * Get a customer given an id.
     * @param id [UUID] to fetch customer.
     * @return [Customer] or null if not found.
     */
    fun getCustomer(id: UUID): Customer? {
        return customerRepo.getCustomer(id)
    }
}
