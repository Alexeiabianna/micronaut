package br.com.zup

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/autor")
class CadastraNovoAutorController {

    @Post
    fun cadastra (@Body request: NovoAutorRequest) {
        println(request)
    }
}