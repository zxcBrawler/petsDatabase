package com.example.petsdatabase.repository

import com.example.petsdatabase.model.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : JpaRepository<Country, Long> {
}