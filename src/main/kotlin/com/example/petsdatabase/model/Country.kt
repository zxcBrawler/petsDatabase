package com.example.petsdatabase.model

import jakarta.persistence.*

@Entity
@Table(name = "country")
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(nullable = false, length = 25)
    var name : String = "",
    @Column(nullable = false, length = 4)
    var dateFormed : Int = 0,
    @Column(nullable = false, length = 50)
    var continent : String = "",

    @OneToMany(mappedBy = "country")
    var manufacturer: Collection<Manufacturer> = arrayListOf()
)
