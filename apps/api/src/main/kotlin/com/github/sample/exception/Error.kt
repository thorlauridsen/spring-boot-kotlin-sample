package com.github.sample.exception

import java.time.OffsetDateTime
import java.time.ZoneOffset

/**
 * Data class representing an error.
 * @param description Description of the error.
 * @param time Time of the error in UTC.
 */
data class Error(
    val description: String,
    val time: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),
)
