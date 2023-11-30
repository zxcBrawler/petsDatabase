package com.example.petsdatabase.repository

import com.example.petsdatabase.model.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodRepository : JpaRepository<Food, Long> {

    fun findFoodByNameFood(name : String) : Food
}