package br.com.alura.forum.controller

import br.com.alura.forum.config.security.TokenService
import br.com.alura.forum.model.dto.TokenDto
import br.com.alura.forum.model.form.LoginForm
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@Profile(value = ["prod", "test"])
@RequestMapping("/auth")
class AuthenticationController(
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    @PostMapping
    fun authenticate(@RequestBody @Valid form: LoginForm): ResponseEntity<Any> {
        return try {
            val authentication =
                authManager.authenticate(form.run { UsernamePasswordAuthenticationToken(form.email, form.pass) })
            val token = tokenService.gerarToken(authentication)
            ResponseEntity.ok(TokenDto(token, "Bearer"))
        } catch (ex: AuthenticationException) {
            ResponseEntity.badRequest().build()
        }
    }
}