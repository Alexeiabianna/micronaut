package br.com.zup

class DetalhesAutoresDto(
    val nome: String,
    val email: String,
    val descricao: String,
) {
    constructor(autor: Autor) : this(
        nome = autor.nome,
        email = autor.email,
        descricao = autor.descricao
    )

}
