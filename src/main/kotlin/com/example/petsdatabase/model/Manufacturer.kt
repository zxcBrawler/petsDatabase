package com.example.petsdatabase.model

import jakarta.persistence.*

@Entity
@Table(name = "manufacturer")
data class Manufacturer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(nullable = false, length = 100)
    var name : String = "",
    @Column(nullable = false)
    var address : String = "",
    @Column(nullable = false, length = 4)
    var yearFormed : Int = 0,

    @OneToMany(mappedBy = "manufacturer")
    var food : Collection<Food> = arrayListOf(),

    @ManyToOne
    var country: Country = Country()
)
