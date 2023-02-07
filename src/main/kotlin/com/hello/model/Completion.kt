package com.hello.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Completion {
    @SerialName("-") NONE,
    @SerialName("Za") CREDIT,
    @SerialName("ZaZk") CREDIT_EXAM,
    @SerialName("Zk") EXAM,
    @SerialName("Klz") CLASSIFIED_CREDIT
}
