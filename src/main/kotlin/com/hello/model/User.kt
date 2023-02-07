package com.hello.model

import kotlinx.serialization.Serializable

@Serializable
data class Predmet(
    val id: Long,
    val email: String,
    val name: String
)