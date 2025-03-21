package com.github.thorlauridsen.dto

import com.github.thorlauridsen.model.CustomerInput
import io.swagger.v3.oas.annotations.media.Schema

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
