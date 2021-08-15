package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/livro")
class CadastraLivroController {

    @Post
    fun cadastra(@Body @Valid request: NovoLivroRequest): HttpResponse<Any> {
        return HttpResponse.ok(request)
    }
}