package com.github.sample.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Controller advisor for handling exceptions.
 * This ensures that whenever an exception is thrown, a proper error response is returned to the client.
 */
@ControllerAdvice
class ControllerAdvisor : ResponseEntityExceptionHandler() {

    private val logger = LoggerFactory.getLogger(ControllerAdvisor::class.java)

    /**
     * Handles all domain exception.
     * If any [DomainException] is thrown, this method will catch it and return a response entity with an [Error].
     * The returned HTTP code will be derived from the specific [DomainException].
     * @param ex The domain exception to handle.
     * @return A response entity with an [Error]
     */
    @ExceptionHandler(value = [DomainException::class])
    fun handleDomainException(ex: DomainException): ResponseEntity<Error> {
        val error = Error(
            description = ex.message.toString(),
        )
        logger.error(ex.message, ex)
        return ResponseEntity.status(ex.httpStatus.value()).body(error)
    }

    /**
     * Handles all exceptions.
     * If any exception is thrown, this method will catch it and return a response entity with an [Error].
     * @param ex The exception to handle.
     * @return A response entity with an [Error]
     */
    @ExceptionHandler
    fun handleEverything(ex: Exception): ResponseEntity<Error> {
        val error = Error(
            description = ex.message.toString(),
        )
        logger.error(ex.message, ex)
        return ResponseEntity.internalServerError().body(error)
    }
}
