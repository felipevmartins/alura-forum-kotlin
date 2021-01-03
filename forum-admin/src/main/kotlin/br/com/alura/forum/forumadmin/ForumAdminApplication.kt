package br.com.alura.forum.forumadmin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAdminServer
@SpringBootApplication
class ForumAdminApplication

fun main(args: Array<String>) {
    runApplication<ForumAdminApplication>(*args)
}




