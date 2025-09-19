package com.github.thorlauridsen.exception

import com.github.thorlauridsen.dto.ErrorDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Controller advisor for handling exceptions.
 * This ensures that whenever an exception is thrown, a proper error response is returned to the client.
 */
@RestControllerAdvice
class ControllerAdvisor : ResponseEntityExceptionHandler() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Handles all domain exception.
     * If any [DomainException] is thrown, this method will catch it and return a response entity with an [ErrorDto].
     * The returned HTTP status code will be derived from the specific [DomainException].
     * @param exception The domain exception to handle.
     * @return A response entity with an [ErrorDto]
     */
    @ExceptionHandler(value = [DomainException::class])
    fun handleDomainException(exception: DomainException): ResponseEntity<ErrorDto> {
        return error(
            exception = exception,
            httpStatus = exception.httpStatus
        )
    }

    /**
     * Handles validation exceptions.
     * Overrides the default handler in ResponseEntityExceptionHandler.
     * If a [MethodArgumentNotValidException] is thrown, this method
     * will catch it and return a response entity with an [ErrorDto].
     * The returned HTTP status code will be 400 Bad Request.
     * @param exception The validation exception to handle.
     * @param headers The headers for the response.
     * @param status The status code for the response.
     * @param request The current request.
     * @return A response entity with an [ErrorDto] containing field errors.
     */
    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val fieldErrors = exception.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Invalid value")
        }
        val errorDto = ErrorDto(
            description = "Validation failed",
            fieldErrors = fieldErrors
        )
        logger.error("Validation failed: $fieldErrors", exception)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto)
    }

    /**
     * Handles all exceptions.
     * If any exception is thrown, this method will catch it and return a response entity with an [ErrorDto].
     * Returns an HTTP 500 status code if no domain exception is thrown.
     * @param exception The exception to handle.
     * @return A response entity with an [ErrorDto]
     */
    @ExceptionHandler
    fun handleEverything(exception: Exception): ResponseEntity<ErrorDto> {
        return error(
            exception = exception,
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    /**
     * Log exception and return a response entity with an [ErrorDto].
     * @param exception [Exception]
     * @param httpStatus [HttpStatus]
     */
    private fun error(exception: Exception, httpStatus: HttpStatus): ResponseEntity<ErrorDto> {
        val errorDto = ErrorDto(
            description = exception.message.toString(),
        )
        logger.error(exception.message, exception)
        return ResponseEntity.status(httpStatus.value()).body(errorDto)
    }
}
