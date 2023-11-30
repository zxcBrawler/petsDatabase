package com.example.petsdatabase.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
open class WebSecurityConfig {
    @Bean
    open fun configure(http: HttpSecurity) : SecurityFilterChain {
       http.invoke {
           authorizeRequests {
                authorize("/login", permitAll)
                authorize("/register", permitAll)

                authorize("/users", hasAnyAuthority("USER", "ADMIN"))
                authorize("/users/search", hasAnyAuthority("USER", "ADMIN"))
                authorize("/users/**", hasAuthority("ADMIN"))
                authorize("/pets", hasAnyAuthority("USER", "ADMIN", "VET"))
                authorize("/pets/**", hasAnyAuthority("ADMIN", "VET"))
                authorize("/food/**",  hasAnyAuthority("ADMIN", "VET"))
                authorize("/address", hasAnyAuthority("USER", "ADMIN"))
                authorize("/address/**", hasAuthority("ADMIN"))
                authorize("/country/**", hasAuthority("ADMIN"))
                authorize("/mans/**", hasAuthority("ADMIN"))
                authorize("/authorities", hasAuthority("ADMIN"))
                authorize("/petFood/**",  hasAnyAuthority("ADMIN", "VET"))
                authorize("/typePet/**",  hasAnyAuthority("ADMIN", "VET"))
                authorize("/userPet/**",  hasAnyAuthority("ADMIN", "VET"))

           }
           formLogin {
               loginPage = "/login"
               defaultSuccessUrl("/main",true)
               permitAll()
           }
           httpBasic {
           }
           logout {
                logoutUrl = "redirect:/login"
               permitAll()
           }
       }
        return http.build()
    }
    @Bean
    open fun getPasswordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}