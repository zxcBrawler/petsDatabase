package com.example.petsdatabase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class PetsDatabaseApplication

fun main(args: Array<String>) {
    runApplication<PetsDatabaseApplication>(*args)
}
