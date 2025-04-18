package com.github.thorlauridsen.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.OffsetDateTime
import java.time.ZoneOffset

/**
 * Data class representing an error.
 * @param description Description of the error.
 * @param time Time of the error in UTC.
 * @param fieldErrors Map of field names to error messages for validation errors.
 */
@Schema(
    description = "Data transfer object for an error",
    example = """
    { 
        "description": "Validation failed",
        "time": "2025-03-13T18:39:00.4900802Z",
        "fieldErrors": {
            "mail": "Invalid email format"
        }
    }
    """,
)
data class ErrorDto(
    val description: String,
    val time: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),
    val fieldErrors: Map<String, String> = emptyMap(),
)
