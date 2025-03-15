package com.github.thorlauridsen.exception

import org.springframework.http.HttpStatus
import java.util.UUID

/**
 * Abstract class representing a domain exception.
 * @param message Description of the domain error.
 * @param httpStatus [HttpStatus] related to the domain error.
 */
abstract class DomainException(
    override val message: String,
    val httpStatus: HttpStatus,
) : Exception()

/**
 * Exception for when a customer could not be found given an id.
 * @param id [UUID]
 */
class CustomerNotFoundException(id: UUID) : DomainException(
    message = "Customer not found with id: $id",
    httpStatus = HttpStatus.NOT_FOUND,
)
