package br.com.alura.forum.config.security

import br.com.alura.forum.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenAuthorizationFilter(
    private val tokenService: TokenService,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getToken(request)
        val valid = tokenService.isValidToken(token.toString())

        if (valid)
            authenticateUser(token.toString())

        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String? {
        return request.run {
            val authorization = this.getHeader("Authorization")
            if (authorization.isNullOrEmpty() || !authorization.startsWith("Bearer"))
                return null

            authorization.substring(7)
        }
    }

    private fun authenticateUser(token: String) {
        val userId = tokenService.getUserId(token)
        val user = userRepository.getOne(userId)
        val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        SecurityContextHolder.getContext().authentication = authentication
    }
}