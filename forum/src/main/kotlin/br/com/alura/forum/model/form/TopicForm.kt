package br.com.alura.forum.model.form

import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.User
import br.com.alura.forum.repository.CourseRepository
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

class TopicForm(
    @field:NotEmpty()
    @field:Size(min = 5, max = 30)
    var title: String,
    @field:NotEmpty
    var message: String,
    @field:NotEmpty
    var courseName: String
) {


    fun convertToTopic(courseRepository: CourseRepository): Topic {
        var course = courseRepository.findByName(courseName)
        return this.let {
            Topic(
                title = it.title,
                message = it.message,
                course = course.get(),
                author = User(id = 1),
                responses = null)
        }
    }
}
