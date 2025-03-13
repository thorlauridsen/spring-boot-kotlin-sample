package com.github.sample.controller

import com.github.sample.Customer
import com.github.sample.CustomerInput
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

interface ICustomerController {

    /**
     * Retrieve a customer given an id.
     * @param id [UUID] of customer.
     * @return [Customer]
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Retrieve a customer given an id",
        description = "Retrieve a customer given an id"
    )
    fun getCustomer(@PathVariable id: UUID): ResponseEntity<Customer>

    /**
     * Save a customer.
     * @param customer [Customer] to save.
     * @return Saved [Customer]
     */
    @PostMapping
    @Operation(
        summary = "Save a customer",
        description = "Save a customer"
    )
    fun saveCustomer(@Valid @RequestBody customer: CustomerInput): ResponseEntity<Customer>
}
