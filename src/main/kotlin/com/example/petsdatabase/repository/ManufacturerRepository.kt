package com.example.petsdatabase.repository

import com.example.petsdatabase.model.Manufacturer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ManufacturerRepository : JpaRepository<Manufacturer, Long> {
}