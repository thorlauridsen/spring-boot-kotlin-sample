package com.github.thorlauridsen.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.OffsetDateTime
import java.time.ZoneOffset

/**
 * Data class representing an error.
 * @param description Description of the error.
 * @param time Time of the error in UTC.
 */
@Schema(
    description = "Data transfer object for an error",
    example = """
    { 
        "description": "Customer not found with id: 3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "time": "2025-03-13T18:39:00.4900802Z"
    }
    """,
)
data class ErrorDto(
    val description: String,
    val time: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),
)
