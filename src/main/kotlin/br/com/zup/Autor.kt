package br.com.zup

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String) {

    @Id
    @GeneratedValue
    var id: Long? = null
    val dataCriacao: LocalDateTime = LocalDateTime.now();

}
