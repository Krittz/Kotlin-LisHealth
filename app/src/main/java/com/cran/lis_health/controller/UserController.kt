package com.cran.lis_health.controller

import android.content.Context
import android.content.SharedPreferences
import com.cran.lis_health.dao.UserDAO
import com.cran.lis_health.model.User
import org.mindrot.jbcrypt.BCrypt


class UserController(private val context: Context) {

    private val userDAO = UserDAO(context)
    private val prefs: SharedPreferences =
        context.getSharedPreferences("UsersPrefs", Context.MODE_PRIVATE)

    fun login(email: String, password: String): String {
        val user = userDAO.getUserByEmail(email)
        if (user == null) {
            return "Usuário não encontrado."
        }
        if (BCrypt.checkpw(password, user.password)) {
            val editor = prefs.edit()
            editor.putLong("userId", user.id)
            editor.putString("userEmail", user.email)
            editor.putString("userName", user.name)
            editor.apply()
            return "Login bem-sucedido!"
        } else {
            return "Senha incorreta."
        }
    }

    fun logout() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.contains("userId")
    }

    fun getCurrentUser(): User? {
        if (!isLoggedIn()) return null
        val id = prefs.getLong("userId", -1)
        val email = prefs.getString("userEmail", null)
        val name = prefs.getString("userName", null)
        return User(id, email!!, "", name!!, name, "", "")

    }

    fun signUp(name: String, email: String, password: String, confirmPassword: String): String {
        // Valida os dados de entrada
        val validationResult = validateInputs(name, email, password, confirmPassword)
        if (validationResult != null) {
            return validationResult
        }


        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())


        val user = User(
            id = 0,
            email = email,
            password = hashedPassword,
            name = name,
            fullName = name,
            cpf = "",
            rg = "",

            )


        userDAO.insertUser(user)
        return "Usuário registrado com sucesso!"
    }


    private fun validateInputs(
        name: String, email: String, password: String, confirmPassword: String
    ): String? {
        if (name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            return "Todos os campos devem ser preenchidos."
        }
        if (password.length < 8) {
            return "A senha deve ter pelo menos 8 caracteres."
        }
        if (!password.matches(".*\\d.*".toRegex())) {
            return "A senha deve conter pelo menos um número."
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*".toRegex())) {
            return "A senha deve conter pelo menos um caractere especial."
        }

        if (password != confirmPassword) {
            return "As senhas não coincidem."
        }
        return null
    }

}