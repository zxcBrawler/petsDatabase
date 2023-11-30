package com.example.petsdatabase.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/main")
class HomeController {

    @GetMapping("")
    fun home() : String {
        return "main"
    }

    @GetMapping("/users")
    fun users() : String {
        return "user/users"
    }

    @GetMapping("/country")
    fun countries() : String {
        return "country/countries"
    }

    @GetMapping("/food")
    fun food() : String {
        return "food/food"
    }

    @GetMapping("/pets")
    fun pets() : String {
        return "pet/pets"
    }

    @GetMapping("/mans")
    fun manufacturers() : String {
        return "manufacturer/mans"
    }

    @GetMapping("/address")
    fun address() : String {
        return "address/addresses"
    }
    @GetMapping("/typePet")
    fun typePet() : String {
        return "typePet/typesPet"
    }
    @GetMapping("/authorities")
    fun authorities() : String {
        return "authorities"
    }

}