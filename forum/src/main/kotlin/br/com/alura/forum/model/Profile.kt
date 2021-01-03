package br.com.alura.forum.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
class Profile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String
) : GrantedAuthority {
    override fun getAuthority(): String {
        return name
    }

}
