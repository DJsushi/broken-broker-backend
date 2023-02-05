package com.hello

import com.hello.data.table.PersonalAccounts
import com.hello.data.table.TradingAccounts
import com.hello.data.table.Users
import com.hello.plugin.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val db = Database.connect(
        url = "jdbc:mariadb://localhost:3306/broken_broker",
        driver = "org.mariadb.jdbc.Driver",
        user = "root",
        password = "012345"
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Users, PersonalAccounts, TradingAccounts)
    }

    configureRouting()
}
