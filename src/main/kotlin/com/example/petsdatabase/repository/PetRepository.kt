package com.example.petsdatabase.repository

import com.example.petsdatabase.model.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : JpaRepository<Pet, Long> {

    fun findPetByName(name : String) : Pet
}