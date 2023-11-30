package com.example.petsdatabase.controller

import com.example.petsdatabase.model.Address
import com.example.petsdatabase.model.Country
import com.example.petsdatabase.model.User
import com.example.petsdatabase.repository.AddressRepository
import com.example.petsdatabase.service.ApiConnection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/address")
class AddressController{

    @GetMapping("")
    fun getAllAddresses(model : Model) : String {
        val address : List<Address> = ApiConnection.restTemplate.getForObject(ApiConnection.URL + "/address",
            arrayListOf<Address>()::class.java)
        model.addAttribute("addressList", address)
        return "address/addresses"
    }
    @GetMapping("/{id}")
    fun getAddressById(@PathVariable("id") addressId : Long, model : Model) : String {
        val address : Address? = ApiConnection.restTemplate.getForObject(ApiConnection.URL + "/address/${addressId}",
            Address::class.java)
        model.addAttribute("address", address)
        return "address/showAddress"
    }

    @GetMapping("/addAddress")
    fun add(model : Model) : String {
        model.addAttribute("address", Address())
        return "address/addAddress"
    }
    @GetMapping("/{id}/editAddress")
    fun edit(@PathVariable ("id") id : Long, model : Model) : String {
        val address : Address? = ApiConnection.restTemplate.getForObject(ApiConnection.URL + "/address/${id}",
            Address::class.java)
        model.addAttribute("address", address)
        return "address/editAddress"
    }

    @PostMapping("")
    fun createAddress(address: Address) : String {
        val headers = org.springframework.http.HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity(address, headers)
        ApiConnection.restTemplate.postForObject(ApiConnection.URL + "/address", request,
            Address::class.java)
        return "redirect:/main"
    }

    @PutMapping("/{id}")
    fun updateAddress(address: Address, @PathVariable("id") id : Long) : String {
        val headers = org.springframework.http.HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity(address, headers)
        ApiConnection.restTemplate.put(ApiConnection.URL + "/address/${id}", request,
            Address::class.java)
        return "redirect:/main"
    }

    @DeleteMapping("/{id}")
    fun deleteAddress(@PathVariable ("id") id : Long) : String {
        ApiConnection.restTemplate.delete(ApiConnection.URL + "/address/${id}")
        return "redirect:/main"
    }
}