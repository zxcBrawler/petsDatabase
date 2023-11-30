package com.example.petsdatabase.controller

import com.example.petsdatabase.model.Address
import com.example.petsdatabase.model.User
import com.example.petsdatabase.repository.AddressRepository
import com.example.petsdatabase.repository.UserRepository
import com.example.petsdatabase.service.ApiConnection
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/users")
class UserController(
    @Autowired private val userRepository: UserRepository,
   ) {

    @GetMapping("")
    fun getAllUsers(model : Model) : String {
        val users : List<User> = ApiConnection.restTemplate.getForObject(ApiConnection.URL + "/users", arrayListOf<User>()::class.java)
        model.addAttribute("userList", users)
        return "user/users"
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable ("id") userId : Long, model : Model) : String {
        val user : User? = ApiConnection.restTemplate.getForObject(ApiConnection.URL + "/users/${userId}", User::class.java)
        model.addAttribute("users", user)
        return "user/showUser"
    }

    @PostMapping("/search")
    fun searchUser(@RequestParam ("username") username : String, model : Model) : String {
        model.addAttribute("users", userRepository.findByUsername(username))
        return "user/search"
    }

    @GetMapping("/{id}/editUser")
    fun edit(@PathVariable ("id") id : Long, model : Model) : String {
        val user : User? = ApiConnection.restTemplate.getForObject(ApiConnection.URL + "/users/${id}", User::class.java)
        val address : List<Address>? = ApiConnection.restTemplate.getForObject(ApiConnection.URL + "/address", arrayListOf<Address>()::class.java)
        model.addAttribute("users", user)
        model.addAttribute("address", address)
        return "user/editUser"
    }
    @GetMapping("/addUser")
    fun add(model : Model) : String {
        val address : List<Address>? = ApiConnection.restTemplate.getForObject(ApiConnection.URL + "/address", arrayListOf<Address>()::class.java)
        model.addAttribute("users", User())
        model.addAttribute("address",address)
        return "user/addUser"
    }

    @PostMapping("")
    fun createUser(user : User, nameAddress : String) : String {
        val headers = org.springframework.http.HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity(user, headers)
        ApiConnection.restTemplate.postForObject(ApiConnection.URL + "/users", request, User::class.java)

        return "redirect:/users"
    }

    @PatchMapping("/{id}")
    fun updateUser(user : User, @PathVariable("id") id : Long, nameAddress : String) : String {
        val headers = org.springframework.http.HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity(user, headers)
        ApiConnection.restTemplate.put(ApiConnection.URL + "/users/${id}", request, User::class.java)
        return "redirect:/users"
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable ("id") id : Long) : String {
        ApiConnection.restTemplate.delete(ApiConnection.URL + "/users/${id}")
        return "redirect:/users"
    }

}