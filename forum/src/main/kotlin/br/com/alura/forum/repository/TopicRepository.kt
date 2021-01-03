package br.com.alura.forum.repository

import br.com.alura.forum.model.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository : JpaRepository<Topic, Long?> {
}