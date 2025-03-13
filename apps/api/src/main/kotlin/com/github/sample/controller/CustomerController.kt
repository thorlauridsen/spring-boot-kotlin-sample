package com.github.sample.controller

import com.github.sample.Customer
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * REST controller for customers.
 */
@Controller
class CustomerController {

    /**
     * Retrieve a customer.
     * @return [Customer]
     */
    @GetMapping("/customer")
    fun getCustomer(): ResponseEntity<Customer> {
        val customer = Customer("Bob", "bob@gmail.com")
        return ResponseEntity.ok(customer)
    }
}
