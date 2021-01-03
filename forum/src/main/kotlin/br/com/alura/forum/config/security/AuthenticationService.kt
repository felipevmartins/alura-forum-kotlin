package br.com.alura.forum.config.security

import br.com.alura.forum.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val repository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByEmail(username.toString())

        if (user.isPresent)
            return user.get()

        throw UsernameNotFoundException("Invalid data")
    }
}