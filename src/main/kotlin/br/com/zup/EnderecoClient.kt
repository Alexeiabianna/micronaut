package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("\${clients.cep.url}")
interface EnderecoClient {

    @Get("{cep}/json")
    fun consulta(cep: String): HttpResponse<EnderecoResponse>
}