package com.hello.plugin

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.apiRouting() {
    route("/api/v1") {
        route("user") {
            get {
                val bytes = ByteArray(10000000)
                call.respondBytes(bytes)
            }

            println(5000.gold)
        }
    }
}

val Int.gold
    get() = this * 100