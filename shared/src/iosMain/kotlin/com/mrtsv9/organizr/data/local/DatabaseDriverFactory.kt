package com.mrtsv9.organizr.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.mrtsv9.organizr.database.TaskDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = TaskDatabase.Schema,
            name = "task.db"
        )
    }
}