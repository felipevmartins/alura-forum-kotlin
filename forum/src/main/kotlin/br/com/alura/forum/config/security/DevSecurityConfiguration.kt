package br.com.alura.forum.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@Profile("dev")
class DevSecurityConfiguration () : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.authorizeRequests {
                it.antMatchers("/**").permitAll()
                    .and().csrf().disable()
            }
        }
    }
}