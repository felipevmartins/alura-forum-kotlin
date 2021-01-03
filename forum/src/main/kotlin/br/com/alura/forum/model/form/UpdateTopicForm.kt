package br.com.alura.forum.model.form

import br.com.alura.forum.model.Topic
import br.com.alura.forum.repository.TopicRepository
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

class UpdateTopicForm(
    @field:NotEmpty()
    @field:Size(min = 5, max = 30)
    var title: String,
    @field:NotEmpty
    var message: String
) {

    fun convertToTopic(id: Long, topicRepository: TopicRepository): Topic {
        var topic = topicRepository.getOne(id)
        topic.title = title
        topic.message = message
        return topic
    }
}
