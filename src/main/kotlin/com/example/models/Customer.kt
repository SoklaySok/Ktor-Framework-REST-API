package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val id: String, val firstName: String, val lastName: String, val email: String)

val customers = mutableListOf<Customer>(
    Customer("1","Soth","Soklay","soklaysok@yahoo.com")
)