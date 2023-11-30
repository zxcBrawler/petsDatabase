package com.example.petsdatabase.controller

import com.example.petsdatabase.model.Pet
import com.example.petsdatabase.repository.PetRepository
import com.example.petsdatabase.repository.TypePetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/pets")
class PetController(
    @Autowired private val petRepository: PetRepository,
    @Autowired private val typePetRepository: TypePetRepository) {
    

    @GetMapping("")
    fun getAllPet(model : Model) : String {
        model.addAttribute("petList", petRepository.findAll())
        return "pet/pets"
    }

    @GetMapping("/{id}")
    fun getPetById(@PathVariable("id") id : Long, model : Model) : String {
        model.addAttribute("pet", petRepository.findById(id))
        return "pet/showPet"
    }

    @GetMapping("/{id}/editPet")
    fun edit(@PathVariable("id") id : Long, model : Model) : String {
        model.addAttribute("pet", petRepository.findById(id))
        model.addAttribute("typePet", typePetRepository.findAll())
        return "pet/editPet"
    }
    @GetMapping("/addPet")
    fun add(model : Model) : String {
        model.addAttribute("pet", Pet())
        model.addAttribute("typePet", typePetRepository.findAll())
        return "pet/addPet"
    }

    @PostMapping("")
    fun createPet(pet : Pet, @RequestParam nameTypePet: String) : String {

        val typePet = typePetRepository.findTypePetByName(nameTypePet)
        pet.typePet = typePet
        petRepository.save(pet)
        return "redirect:/pets"
    }

    @PatchMapping("/{id}")
    fun updatePet(pet : Pet, @PathVariable("id") id : Long, nameTypePet : String) : String {

        val existingPet = petRepository.findById(id)
            .orElse(null)

        val existingType = typePetRepository.findTypePetByName(nameTypePet)

        val updatedPet = existingPet.copy(
            name = pet.name,
            age = pet.age,
            weight = pet.weight,
            typePet = existingType
        )
        petRepository.save(updatedPet)


        return "redirect:/pets"
    }

    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable("id") id : Long) : String {
        petRepository.deleteById(id)
        return "redirect:/pets"
    }
}