package com.hello.model

data class Subject(
    val name: String,
    val abbreviation: String,
    val credits: Int,
    val obligation: Obligation,
    val completion: Completion,
    val faculty: Faculty
)

val x = Faculty.FIT