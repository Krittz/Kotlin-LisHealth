package com.cran.lis_health.model

import java.time.LocalDateTime

data class User(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val fullName: String,
    val cpf: String,
    val rg: String,
    val birth: LocalDateTime,
    val address: Address
)
