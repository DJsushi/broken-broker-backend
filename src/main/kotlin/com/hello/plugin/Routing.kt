@file:Suppress("RedundantSemicolon")

package com.hello.plugin

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        apiRouting()
        home()
    }
}

fun Routing.home() {
    users()
}