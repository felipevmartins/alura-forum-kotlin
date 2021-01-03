package br.com.alura.forum.model.dto

import br.com.alura.forum.model.Topic
import org.springframework.data.domain.Page
import java.time.LocalDateTime

class TopicDto(
    var id: Long?,
    var title: String,
    var message: String,
    var dateCreation: LocalDateTime
) {
    constructor(topico: Topic) : this(topico.id, topico.title, topico.message, topico.dateCreation)

    companion object {
        fun convertListByTopicoList(topics: Page<Topic>): Page<TopicDto> {
            return topics.map { topico -> TopicDto(topico.id, topico.title, topico.message, topico.dateCreation) }
        }

        fun convertByTopic(topic: Topic): TopicDto {
            return topic.let { TopicDto(it.id, it.title, it.message, it.dateCreation) }
        }
    }
}