package com.hello.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        openAPI(path = "/openapi", swaggerFile = "swagger.yaml")
        apiRouting()
        home()
    }
}

fun Routing.home() {
    users()
}