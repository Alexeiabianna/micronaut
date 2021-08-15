package br.com.zup

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [IsbnValidator::class])
annotation class Isbn(
    val message: String = "Formato inválido"
)

@Singleton
class IsbnValidator: ConstraintValidator<Isbn, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Isbn>,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null) {
            return true
        }

        return value.matches("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?\$)[\\d-]+\$".toRegex())
    }

}