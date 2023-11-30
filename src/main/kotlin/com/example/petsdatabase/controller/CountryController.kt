package com.example.petsdatabase.controller



import com.example.petsdatabase.model.Country
import com.example.petsdatabase.repository.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/country")
class CountryController
    (@Autowired private val countryRepository: CountryRepository) {
    

    @GetMapping("")
    fun getAllCountry(model : Model) : String {
        model.addAttribute("countryList", countryRepository.findAll())
        return "country/countries"
    }

    @GetMapping("/{id}")
    fun getCountryById(@PathVariable("id") id : Long, model : Model) : String {
        model.addAttribute("country", countryRepository.findById(id))
        return "country/showCountry"
    }

    @GetMapping("/{id}/editCountry")
    fun edit(@PathVariable("id") id : Long, model : Model) : String {
        model.addAttribute("country", countryRepository.findById(id))
        return "country/editCountry"
    }
    @GetMapping("/addCountry")
    fun add(model : Model) : String {
        model.addAttribute("country", Country())
        return "country/addCountry"
    }

    @PostMapping("")
    fun createCountry(country : Country) : String {
        countryRepository.save(country)
        return "redirect:/country"
    }

    @PatchMapping("/{id}")
    fun updateCountry(country : Country, @PathVariable("id") id : Long) : String {

        val existingCountry = countryRepository.findById(id).orElse(null)

        val updatedCountry = existingCountry.copy(
            name = country.name,
            dateFormed = country.dateFormed,
            continent = country.continent
        )
        countryRepository.save(updatedCountry)

        return "redirect:/country"
    }

    @DeleteMapping("/{id}")
    fun deleteCountry(@PathVariable("id") id : Long) : String {
        countryRepository.deleteById(id)
        return "redirect:/country"
    }
}