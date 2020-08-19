package test.zup.rafael.xyinc.controller.error

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*
import java.util.function.Consumer


@ControllerAdvice
class ErrorHandler() {

    private val LOGGER: Logger = LoggerFactory.getLogger(ErrorHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handleRuntime(e: Exception): ResponseEntity<String>? {
        LOGGER.error(e.message, e)
        return ResponseEntity(e.message.orEmpty(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
            ex: MethodArgumentNotValidException): ResponseEntity<MutableMap<String, String>> {
        val errors: MutableMap<String, String> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            errors[fieldName] = error.getDefaultMessage().orEmpty()
        })
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(
            ex: BindException): ResponseEntity<MutableMap<String, String>> {
        val errors: MutableMap<String, String> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            errors[fieldName] = error.getDefaultMessage().orEmpty()
        })
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

}