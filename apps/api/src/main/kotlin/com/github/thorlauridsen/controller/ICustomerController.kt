package com.github.thorlauridsen.controller

import com.github.thorlauridsen.dto.CustomerDto
import com.github.thorlauridsen.dto.CustomerInputDto
import com.github.thorlauridsen.dto.ErrorDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

const val CUSTOMER_BASE_ENDPOINT = "/customers"

@Tag(name = "Customer Controller", description = "API for managing customers")
@RequestMapping(CUSTOMER_BASE_ENDPOINT)
interface ICustomerController {

    /**
     * Retrieve a customer given an id.
     * @param id [UUID] of customer.
     * @return [CustomerDto]
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Retrieve a customer given an id",
        description = "Retrieve a customer given an id"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved customer"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Customer not found with given id",
        content = [Content(schema = Schema(implementation = ErrorDto::class))]
    )
    fun get(
        @Parameter(description = "UUID of the customer to retrieve", required = true)
        @PathVariable id: UUID
    ): ResponseEntity<CustomerDto>

    /**
     * Save a customer.
     * @param customer [CustomerInputDto] to save.
     * @return Saved [CustomerDto]
     */
    @PostMapping
    @Operation(
        summary = "Save a customer",
        description = "Save a customer"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Customer successfully created"
    )
    fun post(@Valid @RequestBody customer: CustomerInputDto): ResponseEntity<CustomerDto>
}
