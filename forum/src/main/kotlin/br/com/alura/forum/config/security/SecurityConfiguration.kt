package br.com.alura.forum.config.security

import br.com.alura.forum.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@Profile(value = ["prod", "test"])
class SecurityConfiguration (
    private val authenticationService: AuthenticationService,
    private val tokenService: TokenService,
    private val userRepository: UserRepository
) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.let {
            it.userDetailsService(authenticationService).passwordEncoder(BCryptPasswordEncoder())
        }
    }

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.authorizeRequests {
                it.antMatchers(HttpMethod.GET, "/topics").permitAll()
                    .antMatchers(HttpMethod.GET, "/topics/*").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/topics/*").hasRole("MODERATOR")
                    .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/auth").permitAll()
                    .anyRequest().authenticated()
                    .and().csrf().disable().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().addFilterBefore(
                        TokenAuthorizationFilter(tokenService, userRepository),
                        UsernamePasswordAuthenticationFilter::class.java
                    )

            }
        }
    }

    override fun configure(web: WebSecurity?) {
        web!!.ignoring()
            .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**")
    }
}