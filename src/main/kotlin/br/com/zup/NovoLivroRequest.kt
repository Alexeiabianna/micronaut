package br.com.zup

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class NovoLivroRequest(
    @field:NotBlank val titulo: String,
    @field:NotBlank val autor: Long,
    @field:NotBlank @field:Isbn val isbn: String
) {
}