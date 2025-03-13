package com.github.sample.controller

import com.github.sample.Customer
import com.github.sample.CustomerInput
import com.github.sample.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

/**
 * REST controller for customers.
 * This controller consists of endpoints for:
 * - Saving customers.
 * - Fetching customers.
 *
 * @param customerService [CustomerService] service layer.
 */
@Controller
@RequestMapping("/customer")
class CustomerController(private val customerService: CustomerService) : ICustomerController {

    /**
     * Retrieve a customer given an id.
     * @param id [UUID] of customer.
     * @return [Customer]
     */
    override fun getCustomer(id: UUID): ResponseEntity<Customer> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity.ok(customer)
    }

    /**
     * Save a customer.
     * @param customer [Customer] to save.
     * @return Saved [Customer]
     */
    override fun getCustomer(customer: CustomerInput): ResponseEntity<Customer> {
        return ResponseEntity.ok(customerService.saveCustomer(customer))
    }
}
