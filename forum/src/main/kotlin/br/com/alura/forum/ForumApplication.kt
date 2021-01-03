package br.com.alura.forum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.web.config.EnableSpringDataWebSupport
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableCaching
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
class ForumApplication

fun main(args: Array<String>) {
    runApplication<ForumApplication>(*args)
}
