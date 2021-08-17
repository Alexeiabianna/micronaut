package br.com.zup

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
class CadastroNovoAutorTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `deve cadastrar um novo autor no sistema`() {

        //cenario
        val novoAutorRequest = NovoAutorRequest("Rafael Ponte", "rafael.ponte@zup.com.br",
            "principe dos oceanos","91420560","386")

        val enderecoResponse = EnderecoResponse("Rua das Laranjeiras", "Rio de Janeiro", "RJ")

        Mockito.`when`(enderecoClient.consulta(novoAutorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autor", novoAutorRequest)


        //acao
        val response = client.toBlocking().exchange(request, Any::class.java)

        //corretude
        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autor/(.*?)".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }
}