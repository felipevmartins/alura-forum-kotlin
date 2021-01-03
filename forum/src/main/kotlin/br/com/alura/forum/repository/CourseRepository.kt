package br.com.alura.forum.repository

import br.com.alura.forum.model.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CourseRepository : JpaRepository<Course, Long?> {
    fun findByName(nomeCurso: String): Optional<Course>
}