package com.hello.data.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

abstract class Accounts(name: String) : IntIdTable(name) {
    val name = varchar("name", 100)
    val dateCreated = timestamp("date_created")
    val user = reference("user_id", Users)
}