package br.com.alura.forum.repository

import br.com.alura.forum.model.Course
import org.junit.jupiter.api.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestEntityManager
class CourseRepositoryTest(
    @Autowired val repository: CourseRepository,
    @Autowired val em: TestEntityManager
) {

    @BeforeEach
    fun setup(){
        val html5 = Course(
            name = "HTML 5",
            category = "Programação"
        )

        em.persist(html5)
    }

    @Test
    fun findCourseByNomeTestExist(){
        val courseName = "HTML 5"

        val course = repository.findByName(courseName)

        Assertions.assertNotNull(course.get())
        Assertions.assertEquals(courseName, course.get().name)
    }

    @Test
    fun findCourseByNomeTestNotExists(){
        val courseName = "Java 25"

        val course = repository.findByName(courseName)

        Assertions.assertEquals(false, course.isPresent)
    }

}