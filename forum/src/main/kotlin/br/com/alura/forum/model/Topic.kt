package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String = "",
    var message: String = "",
    var dateCreation: LocalDateTime = LocalDateTime.now(),
    @Enumerated(EnumType.STRING)
    var status: StatusTopic = StatusTopic.UNSOLVED,
    @ManyToOne
    var author: User,
    @ManyToOne
    var course: Course,
    @OneToMany
    var responses: List<Response>?
) {

}