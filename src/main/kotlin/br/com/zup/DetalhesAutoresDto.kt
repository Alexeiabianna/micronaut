package br.com.zup

class DetalhesAutoresDto(autor: Autor) {
    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
