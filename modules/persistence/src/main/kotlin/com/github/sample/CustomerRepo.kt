package com.github.sample

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * Exposed customer repository.
 * This repository is responsible for:
 * - Saving customers to the database.
 * - Fetching customers from the database.
 */
@Repository
class CustomerRepo {

    private val logger = LoggerFactory.getLogger(CustomerRepo::class.java)

    /**
     * Save a customer to the database.
     * @param customer [CustomerInput] to save.
     * @throws IllegalStateException if customer not found in database after saving.
     * @return [Customer] retrieved from database.
     */
    fun saveCustomer(customer: CustomerInput): Customer {
        return transaction {
            logger.info("Saving customer to database: $customer")
            val id = CustomerTable.insertAndGetId {
                it[mail] = customer.mail
            }
            logger.info("Saved customer with id $id in database")

            getCustomer(id.value) ?: error("Could not save customer with mail: $customer")
        }
    }

    /**
     * Get a customer from the database given an id.
     * @param id [UUID] to fetch customer.
     * @return [Customer] or null if not found.
     */
    fun getCustomer(id: UUID): Customer? {
        logger.info("Retrieving customer with id: $id from database")

        return transaction {
            val customer = CustomerTable
                .selectAll()
                .where { CustomerTable.id eq id }
                .map { mapToCustomer(it) }
                .firstOrNull()

            logger.info("Found customer $customer in database")
            customer
        }
    }

    /**
     * Map ResultRow to a Customer.
     * @param row [ResultRow]
     * @return [Customer]
     */
    private fun mapToCustomer(row: ResultRow): Customer {
        return Customer(
            id = row[CustomerTable.id].value,
            mail = row[CustomerTable.mail],
        )
    }
}
