package com.hello.data.table

import org.jetbrains.exposed.sql.Column
import java.math.BigDecimal

object TradingAccounts : Accounts(name = "trading_accounts") {
    val totalTraded: Column<BigDecimal> = decimal("total_traded", 11, 2)
}