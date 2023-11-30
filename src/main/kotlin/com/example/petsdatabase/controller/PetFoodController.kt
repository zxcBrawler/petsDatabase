package com.example.petsdatabase.controller

import com.example.petsdatabase.repository.FoodRepository
import com.example.petsdatabase.repository.PetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/petFood")
class PetFoodController(
    @Autowired private val petRepository: PetRepository,
    @Autowired private val foodRepository: FoodRepository,
) {
    @GetMapping("")
    fun getAllPetFood(model : Model) : String {
        model.addAttribute("petList", petRepository.findAll())
        model.addAttribute("foodList", foodRepository.findAll())
        return "petFood/petsFood"
    }
    @PostMapping("")
    fun addPetFood(@RequestParam namePet : String, @RequestParam nameFood : String) : String{

        val pet = petRepository.findPetByName(namePet)
        val food = foodRepository.findFoodByNameFood(nameFood)
        pet.food = arrayListOf(food)
        food.pet = arrayListOf(pet)
        petRepository.save(pet)
        foodRepository.save(food)

        return "petFood/petsFood"
    }
}