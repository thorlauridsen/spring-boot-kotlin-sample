package com.github.sample

/**
 * Model data class representing a customer.
 * @param name Full name of customer.
 * @param mail Mail address of customer.
 */
data class Customer(
    val name: String,
    val mail: String,
)
