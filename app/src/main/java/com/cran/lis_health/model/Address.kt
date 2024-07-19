package com.cran.lis_health.model

data class Address(
    val id: Long,
    val street: String,
    var region: String,
    val number: Int,
    val complement: String,
    val cep: String,
    val annotation: String
)
