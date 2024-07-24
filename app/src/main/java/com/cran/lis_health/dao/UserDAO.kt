package com.cran.lis_health.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.cran.lis_health.model.User

class UserDAO(context: Context) {

    private val dbHelper: DatabaseHelper = DatabaseHelper.getInstance(context)

    fun insertUser(user: User) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("email", user.email)
            put("password", user.password)
            put("name", user.name)
            put("fullName", user.fullName)
            put("cpf", user.cpf)
            put("rg", user.rg)
        }
        db.insert("users", null, values)
    }

    fun getUserByEmail(email: String): User? {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "users",
            arrayOf("id", "email", "password", "name", "fullName", "cpf", "rg"),
            "email = ?",
            arrayOf(email),
            null,
            null,
            null
        )
        return if (cursor.moveToFirst()) {
            User(
                id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                email = cursor.getString(cursor.getColumnIndexOrThrow("email")),
                password = cursor.getString(cursor.getColumnIndexOrThrow("password")),
                name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullName")),
                cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf")),
                rg = cursor.getString(cursor.getColumnIndexOrThrow("rg"))
            ).also {
                cursor.close()
            }
        } else {
            cursor.close()
            null
        }
    }
}