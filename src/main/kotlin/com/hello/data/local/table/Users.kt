package com.hello.data.local.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Users : IntIdTable(name = "users") {
    val email: Column<String> = varchar("email", 100)
    val password: Column<String> = varchar("password", 100)
    val username: Column<String> = varchar("username", 50)
}