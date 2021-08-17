package br.com.zup

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutorControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor
    lateinit var enderecoResponse: EnderecoResponse
    lateinit var endereco: Endereco

    @BeforeEach
    internal fun setup() {
        enderecoResponse = EnderecoResponse("Rua da Gruta","Porto Alegre","RS")
        endereco = Endereco(enderecoResponse, "37")

        autor = Autor("Rafael Ponte", "rafael.ponte@zup.com.br", "marajá dos legados", endereco)
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve buscar um autor quando um email valido eh informado`() {

        val response = client.toBlocking().exchange("/autor/get?email=${autor.email}", DetalhesAutoresDto::class.java)
        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)

    }
}