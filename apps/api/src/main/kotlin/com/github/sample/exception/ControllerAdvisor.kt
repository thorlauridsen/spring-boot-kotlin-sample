package com.github.sample.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
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
     * The returned HTTP status code will be derived from the specific [DomainException].
     * @param exception The domain exception to handle.
     * @return A response entity with an [Error]
     */
    @ExceptionHandler(value = [DomainException::class])
    fun handleDomainException(exception: DomainException): ResponseEntity<Error> {
        return error(
            exception = exception,
            httpStatus = exception.httpStatus
        )
    }

    /**
     * Handles all exceptions.
     * If any exception is thrown, this method will catch it and return a response entity with an [Error].
     * Returns an HTTP 500 status code if no domain exception is thrown.
     * @param exception The exception to handle.
     * @return A response entity with an [Error]
     */
    @ExceptionHandler
    fun handleEverything(exception: Exception): ResponseEntity<Error> {
        return error(
            exception = exception,
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    /**
     * Log exception and return a response entity with an [Error].
     * @param exception [Exception]
     * @param httpStatus [HttpStatus]
     */
    private fun error(exception: Exception, httpStatus: HttpStatus): ResponseEntity<Error> {
        val error = Error(
            description = exception.message.toString(),
        )
        logger.error(exception.message, exception)
        return ResponseEntity.status(httpStatus.value()).body(error)
    }
}
