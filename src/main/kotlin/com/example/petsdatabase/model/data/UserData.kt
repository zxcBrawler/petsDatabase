package com.example.petsdatabase.model.data

import jakarta.persistence.Column
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull
import java.io.Serializable

class UserData : Serializable {
    var name : String = ""
    var surname : String = ""
    var username : String = ""
    var age : Int = 0
    var password : String = ""
}