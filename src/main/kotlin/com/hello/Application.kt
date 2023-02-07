@file:Suppress("NonAsciiCharacters")

package com.hello

import com.hello.data.local.table.Users
import com.hello.plugin.configureRouting
import com.hello.vut_subject_market_backend.BuildConfig
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()

    }
    val db = Database.connect(
        url = "jdbc:mariadb://localhost:3306/vut_subject_market",
        driver = "org.mariadb.jdbc.Driver",
        user = BuildConfig.DB_USERNAME,
        password = BuildConfig.DB_PASSWORD
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Users)
    }

    configureRouting()
}
