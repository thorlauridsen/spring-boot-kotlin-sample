package com.github.thorlauridsen.dto

import com.github.thorlauridsen.model.CustomerInput
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

/**
 * Data transfer object used to create a new customer.
 * @param mail Mail address of customer.
 */
@Schema(
    description = "Data transfer object for creating a new customer",
    example = """
    { 
        "mail": "bob@gmail.com"
    }
    """,
)
data class CustomerInputDto(
    @get:NotBlank(message = "Email is required")
    @get:Email(message = "Invalid email format")
    val mail: String,
) {

    /**
     * Convert a [CustomerInputDto] to [CustomerInput].
     * @return [CustomerInput]
     */
    fun toModel(): CustomerInput {
        return CustomerInput(
            mail = mail
        )
    }
}
