package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Response(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var mensagem: String = "",
    @ManyToOne
    var topic: Topic,
    var dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    var autor: User,
    var solution: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Response

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}