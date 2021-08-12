package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.transaction.Transactional

@Controller("/autor/get")
class BuscaAutorController(val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun busca(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if(email.isBlank()) {
            val lista = autorRepository.findAll()
            val resposta = lista.map { autor -> DetalhesAutoresDto(autor) }

            return HttpResponse.ok(resposta)
        }
        //val optionalAutor = autorRepository.findByEmail(email)
        val possivelAutor = autorRepository.buscaPorEmail(email)
        if(possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()

        return HttpResponse.ok(DetalhesAutoresDto(autor))
    }
}