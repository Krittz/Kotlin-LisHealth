package com.cran.lis_health.model

import java.time.LocalDateTime

data class Register(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
  //  val user: User,
    var isExpandable: Boolean = false
)