package com.github.thorlauridsen.dto

import com.github.thorlauridsen.Customer
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

/**
 * Data transfer object representing a customer.
 * @param id [UUID] of customer.
 * @param mail Mail address of customer.
 */
@Schema(
    description = "Data transfer object for a customer",
    example = """
    { 
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "mail": "bob@gmail.com"
    }
    """,
)
data class CustomerDto(
    val id: UUID,
    val mail: String,
)

/**
 * Convert a [Customer] to [CustomerDto].
 * @return [CustomerDto]
 */
fun Customer.toDto(): CustomerDto {
    return CustomerDto(
        id = id,
        mail = mail
    )
}
