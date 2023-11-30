package com.example.petsdatabase.controller

import com.example.petsdatabase.model.Food
import com.example.petsdatabase.repository.CountryRepository
import com.example.petsdatabase.repository.FoodRepository
import com.example.petsdatabase.repository.TypePetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/food")
class FoodController
    (@Autowired private val foodRepository: FoodRepository,
) {
    

    @GetMapping("")
    fun getAllFood(model : Model) : String {
        model.addAttribute("foodList", foodRepository.findAll())
        return "food/food"
    }

    @GetMapping("/{id}")
    fun getFoodById(@PathVariable("id") id : Long, model : Model) : String {
        model.addAttribute("food", foodRepository.findById(id))
        return "food/showFood"
    }

    @GetMapping("/{id}/editFood")
    fun edit(@PathVariable("id") id : Long, model : Model) : String {
        model.addAttribute("food", foodRepository.findById(id))
        return "food/editFood"
    }
    @GetMapping("/addFood")
    fun add(model : Model) : String {
        model.addAttribute("food", Food())
        return "food/addFood"
    }

    @PostMapping("")
    fun createFood(food : Food) : String {
        foodRepository.save(food)
        return "redirect:/food"
    }

    @PatchMapping("/{id}")
    fun updateFood(food : Food, @PathVariable("id") id : Long) : String {

        val existingFood = foodRepository.findById(id).orElse(null)

        val updatedFood = existingFood.copy(
            nameFood = food.nameFood,
            mainIngredient = food.mainIngredient
        )
        foodRepository.save(updatedFood)

        return "redirect:/food"
    }

    @DeleteMapping("/{id}")
    fun deleteFood(@PathVariable("id") id : Long) : String {
        foodRepository.deleteById(id)
        return "redirect:/food"
    }
}
