package br.com.alura.forum.config.security

import br.com.alura.forum.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(
    @Value("\${forum.jwt.expiration}")
    private val expiration: String,
    @Value("\${forum.jwt.secret}")
    private val secret: String
) {

    fun gerarToken(authentication: Authentication): String {
        var user: User = authentication.principal as User

        var now = Date()

        return Jwts.builder()
            .setIssuer(user.name)
            .setSubject(user.id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + expiration.toLong()))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }

    }

    fun getUserId(token: String): Long {
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        return claims.subject.toLong()

    }
}