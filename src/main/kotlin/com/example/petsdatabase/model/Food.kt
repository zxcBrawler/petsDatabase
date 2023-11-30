package com.example.petsdatabase.model

import jakarta.persistence.*

@Entity
@Table(name = "food")
data class Food(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(nullable = false, length = 25)
    var nameFood : String = "",
    @Column(nullable = false, length = 100)
    var mainIngredient : String = "",

    @ManyToOne
    val manufacturer : Manufacturer = Manufacturer(),

    @ManyToMany
    @JoinTable(
        name = "pet_food",
        joinColumns = [JoinColumn(name = "pet_id")],
        inverseJoinColumns = [JoinColumn(name = "food_id")])
    var pet: List<Pet> = arrayListOf()

)
