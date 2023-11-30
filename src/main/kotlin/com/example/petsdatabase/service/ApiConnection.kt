package com.example.petsdatabase.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
object ApiConnection {
    const val URL = "http://localhost:9090/api"
    val restTemplate = RestTemplate()
}