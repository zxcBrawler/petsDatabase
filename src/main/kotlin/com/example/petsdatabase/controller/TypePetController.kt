package com.example.petsdatabase.controller

import com.example.petsdatabase.model.Address
import com.example.petsdatabase.model.TypePet
import com.example.petsdatabase.repository.TypePetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/typePet")
class TypePetController (@Autowired private val typePetRepository: TypePetRepository) {

    @GetMapping("")
    fun getAllTypes(model : Model) : String {
        model.addAttribute("typeList", typePetRepository.findAll())
        return "typePet/typesPet"
    }
    @GetMapping("/addTypePet")
    fun add(model : Model) : String {
        model.addAttribute("typePet", TypePet())
        return "typePet/addTypePet"
    }

    @PostMapping("")
    fun createType(typePet: TypePet) : String {
        typePetRepository.save(typePet)
        return "redirect:/typePet"
    }

}