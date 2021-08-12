package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autor")
class BuscaAutorController(val autorRepository: AutorRepository) {

    @Get
    fun busca(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if(email.isBlank()) {
            val lista = autorRepository.findAll()
            val resposta = lista.map { autor -> DetalhesAutoresDto(autor) }

            return HttpResponse.ok(resposta)
        }
        val optionalAutor = autorRepository.findByEmail(email)
        if(optionalAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = optionalAutor.get()

        return HttpResponse.ok(DetalhesAutoresDto(autor))
    }
}