package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional

@Controller("/autor/{id}")
class AlteraDescricaoAutorController(val autorRepository: AutorRepository) {

    @Put
    @Transactional
    fun update(@PathVariable id: Long, descricao: String): HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(id)

        if(possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()

        autor.descricao = descricao
        autorRepository.update(autor)

        return HttpResponse.ok(DetalhesAutoresDto(autor))
    }
}