package com.example.petsdatabase.controller.auth

import com.example.petsdatabase.model.TypeRole
import com.example.petsdatabase.model.User
import com.example.petsdatabase.model.data.UserData
import com.example.petsdatabase.repository.AddressRepository
import com.example.petsdatabase.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping("/register")
class RegistrationController (
    @Autowired private val userService: UserService,
    @Autowired private val addressRepository: AddressRepository,

) {

    @GetMapping("")
    fun register(model: Model): String {
        model.addAttribute("userData", UserData())
        model.addAttribute("address", addressRepository.findAll())
        return "account/register"
    }

    @PostMapping("")
    fun register(body: UserData, @RequestParam nameAddress : String) : String {
        val user = User()
        val address = addressRepository.findByName(nameAddress)
        val bcrypt = BCryptPasswordEncoder()

        user.address = address
        user.username = body.username
        user.name = body.name
        user.surname = body.surname
        user.password = bcrypt.encode(body.password)
        user.age = body.age

        user.roles = Collections.singleton(TypeRole.USER)
        userService.saveUser(user)
        return "account/register"
    }
}