package com.example.petsdatabase.repository

import com.example.petsdatabase.model.Address
import com.sun.nio.sctp.PeerAddressChangeNotification.AddressChangeEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long> {

    fun findByName(name : String) : Address
}