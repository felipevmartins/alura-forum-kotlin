package br.com.alura.forum.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.persistence.FetchType


@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String = "",
    var email: String = "",
    var pass: String = "",
    @ManyToMany(fetch = FetchType.EAGER)
    var profiles: Set<Profile> = HashSet()
) : UserDetails {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return profiles.map {
            SimpleGrantedAuthority(it.name)
        }
            .toMutableList()
    }

    override fun getPassword(): String {
        return pass
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}