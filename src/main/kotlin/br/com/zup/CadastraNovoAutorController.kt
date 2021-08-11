package br.com.zup

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autor")
class CadastraNovoAutorController {

    @Post
    fun cadastra (@Body @Valid request: NovoAutorRequest) {
        println(request)
    }
}