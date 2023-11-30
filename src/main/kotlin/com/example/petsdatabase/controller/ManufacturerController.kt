package com.example.petsdatabase.controller

import com.example.petsdatabase.model.Manufacturer
import com.example.petsdatabase.repository.FoodRepository
import com.example.petsdatabase.repository.ManufacturerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/mans")
class ManufacturerController 
    (@Autowired private val manufacturerRepository: ManufacturerRepository) {
   

    @GetMapping("")
    fun getAllMan(model : Model) : String {
        model.addAttribute("manList", manufacturerRepository.findAll())
        return "manufacturer/mans"
    }

    @GetMapping("/{id}")
    fun getManById(@PathVariable("id") id : Long, model : Model) : String {
        model.addAttribute("man", manufacturerRepository.findById(id))
        return "manufacturer/showMan"
    }

    @GetMapping("/{id}/editMan")
    fun edit(@PathVariable("id") id : Long, model : Model) : String {
        model.addAttribute("man", manufacturerRepository.findById(id))
        return "manufacturer/editMan"
    }
    @GetMapping("/addMan")
    fun add(model : Model) : String {
        model.addAttribute("man", Manufacturer())
        return "manufacturer/addMan"
    }

    @PostMapping("")
    fun createMan(man : Manufacturer) : String {
        manufacturerRepository.save(man)
        return "redirect:/mans"
    }

    @PatchMapping("/{id}")
    fun updateMan(man : Manufacturer, @PathVariable("id") id : Long) : String {

        val existingManufacturer = manufacturerRepository.findById(id)
            .orElse(null)

        val updatedManufacturer = existingManufacturer.copy(
            name = man.name,
            address = man.address,
            yearFormed = man.yearFormed
        )
        manufacturerRepository.save(updatedManufacturer)

        return "redirect:/mans"
    }

    @DeleteMapping("/{id}")
    fun deleteMan(@PathVariable("id") id : Long) : String {
        manufacturerRepository.deleteById(id)
        return "redirect:/mans"
    }
}