package br.com.alura.forum.controller

import br.com.alura.forum.model.dto.DetailTopicDto
import br.com.alura.forum.model.form.TopicForm
import br.com.alura.forum.model.dto.TopicDto
import br.com.alura.forum.model.form.UpdateTopicForm
import br.com.alura.forum.repository.CourseRepository
import br.com.alura.forum.repository.TopicRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(
    private val repository: TopicRepository,
    private val courseRepository: CourseRepository
) {

    @GetMapping
    @Cacheable(value = ["topicList"])
    fun list(pageable: Pageable): Page<TopicDto> {

        return TopicDto.convertListByTopicoList(repository.findAll(pageable))
    }

    @PostMapping
    @CacheEvict(value = ["topicList"], allEntries = true)
    fun create(
        @RequestBody @Valid topicoForm: TopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicDto> {
        val topico = topicoForm.convertToTopic(courseRepository)
        repository.save(topico)

        val uri = uriBuilder.path("/topics/{id}").buildAndExpand(topico.id).toUri()

        return ResponseEntity.created(uri).body(TopicDto(topico))
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): ResponseEntity<DetailTopicDto> {
        var topico = repository.findById(id)

        if (topico.isPresent) {
            return ResponseEntity.ok(DetailTopicDto.convertByTopico(topico.get()))
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @CacheEvict(value = ["topicList"], allEntries = true)
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid atualizaTopicoForm: UpdateTopicForm
    ): ResponseEntity<TopicDto> {
        var topico = repository.findById(id)

        if (topico.isPresent) {
            val topicoAtualizado = atualizaTopicoForm.convertToTopic(id, repository)
            repository.save(topicoAtualizado)
            return ResponseEntity.ok(TopicDto(topicoAtualizado))
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("{id}")
    @CacheEvict(value = ["topicList"], allEntries = true)
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        var topico = repository.findById(id)

        if (topico.isPresent) {
            repository.deleteById(id)
            return ResponseEntity.ok().build()
        }

        return ResponseEntity.notFound().build();
    }
}