package com.example.petsdatabase.controller.authorities

import com.example.petsdatabase.model.TypeRole
import com.example.petsdatabase.model.User
import com.example.petsdatabase.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/authorities")
@PreAuthorize("hasAnyAuthority('ADMIN')")
class AuthoritiesController  (
    @Autowired private val userRepository: UserRepository,
    )  {
    @GetMapping("")
    fun authorities(model: Model): String {
        model.addAttribute("userList", userRepository.findAll())
        return "authorities"
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId : Long, model : Model) : String {
        model.addAttribute("user", userRepository.findById(userId))
        model.addAttribute("roles", TypeRole.entries.toTypedArray())
        return "changeAuthorities"
    }

    @PatchMapping("/{id}")
    fun updateUser(user : User, @PathVariable("id") id : Long, @RequestParam("roles") role : String) : String {
        val existingUser = userRepository.findById(id).orElse(null)

        val updatedUser = existingUser.copy(
            roles = mutableSetOf(TypeRole.valueOf(role))
        )
        userRepository.save(updatedUser)
        return "redirect:/authorities"
    }
}