package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autor")
class CadastraNovoAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastra (@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        println("Request => $request")
        val autor = request.toModel()
        println("Model => ${autor.nome}")
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autor/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
    }

    @Get
    fun lista(): HttpResponse<List<DetalhesAutoresDto>> {
        val autores = autorRepository.findAll()

        val resposta = autores.map { autor -> DetalhesAutoresDto(autor) }

        return HttpResponse.ok(resposta)
    }
}