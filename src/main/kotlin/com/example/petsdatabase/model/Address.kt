package com.example.petsdatabase.model

import jakarta.persistence.*

@Entity
@Table(name = "address")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    @Column(unique = true)
    var name : String = "",
    var currentAddress : String = "",

    @OneToMany(mappedBy = "address")
    var user: Collection<User> = arrayListOf()
)
