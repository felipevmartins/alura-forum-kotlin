package br.com.alura.forum.model.dto

import java.time.LocalDateTime

class ResponseDto(
    val id: Long?,
    val message: String,
    val dateCreation: LocalDateTime,
    val authorName: String
) {
}