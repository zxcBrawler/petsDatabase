package com.example.petsdatabase.service

import com.example.petsdatabase.model.User
import com.example.petsdatabase.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service


@Service
class UserService (@Autowired private val userRepository: UserRepository) : UserDetailsService {

    fun saveUser(user : User): User {
        return this.userRepository.save(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)

        val grantedAuthorities: MutableSet<GrantedAuthority> = HashSet()
        for (role in user.roles!!) {
            grantedAuthorities.add(SimpleGrantedAuthority(role.name))
        }
        return org.springframework.security.core.userdetails.User(user.username, user.password, grantedAuthorities)
    }
}