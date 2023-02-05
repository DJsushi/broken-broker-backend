package com.hello.plugin

import com.hello.data.entity.User
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.users() {
    route("/user") {
        new()
    }
}

private fun Route.new() {
    get("/new") {
        val user = transaction {
            User.new {
                email = "djsushi123@gmail.com"
                password = "qwerty123"
                username = "djsushi123"
            }
        }
        call.respondText(user.toString())
    }
}