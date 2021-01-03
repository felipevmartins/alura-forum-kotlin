package br.com.alura.forum.model.dto

import br.com.alura.forum.model.StatusTopic
import br.com.alura.forum.model.Topic
import java.time.LocalDateTime

class DetailTopicDto(
    val id: Long?,
    val title: String,
    val message: String,
    val dateCreation: LocalDateTime,
    val authorName: String,
    val status: StatusTopic,
    val responses: List<ResponseDto>?
) {
    companion object {
        fun convertByTopico(topic: Topic): DetailTopicDto {
            return topic.let { topic ->
                DetailTopicDto(
                    topic.id,
                    topic.title,
                    topic.message,
                    topic.dateCreation,
                    topic.author.name,
                    topic.status,
                    topic.responses?.map { response ->
                        ResponseDto(
                            response.id,
                            response.mensagem,
                            response.dataCriacao,
                            response.autor.name)
                    }
                )
            }
        }
    }
}