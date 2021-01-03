package br.com.alura.forum.config.validation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ErrorValidationHandler(
    val messageSource: MessageSource
) {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors = exception.bindingResult.fieldErrors.map {
            FormError(it.field, messageSource.getMessage(it, LocaleContextHolder.getLocale()))
        }

        return ResponseEntity.badRequest().body(errors)
    }
}