package com.example.petsdatabase.model

import org.springframework.security.core.GrantedAuthority

enum class TypeRole : GrantedAuthority {
   USER, ADMIN, VET;

    override fun getAuthority(): String = name

}