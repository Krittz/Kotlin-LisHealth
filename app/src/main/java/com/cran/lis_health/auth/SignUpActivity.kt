package com.cran.lis_health.auth

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.cran.lis_health.R
import com.cran.lis_health.controller.UserController

class SignUpActivity : AppCompatActivity() {

    private lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        userController = UserController(this)

        val signupFullName: AppCompatEditText = findViewById(R.id.signupName)
        val signupEmail: AppCompatEditText = findViewById(R.id.signupEmail)
        val signupPassword: AppCompatEditText = findViewById(R.id.signupPassword)
        val signupConfirmPassword: AppCompatEditText = findViewById(R.id.signupConfirmPassword)
        val btnSignup: AppCompatButton = findViewById(R.id.btnSignup)
        val btnRedirectLogin: AppCompatTextView = findViewById(R.id.redirectLogin)

        btnRedirectLogin.setOnClickListener {
            redirectLogin()
        }
        btnSignup.setOnClickListener {
            val fullName = signupFullName.text.toString().trim()
            val email = signupEmail.text.toString().trim()
            val password = signupPassword.text.toString().trim()
            val confirmPassword = signupConfirmPassword.text.toString().trim()

            // Validar entradas
            val validationMessage = validateInputs(fullName, email, password, confirmPassword)
            if (validationMessage != null) {
                Toast.makeText(this, validationMessage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Registrar usuário
            val resultMessage = userController.signUp(fullName, email, password, confirmPassword)
            Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()
            if (resultMessage == "Usuário registrado com sucesso!") {
                redirectLogin()
            }
        }
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

    private fun redirectLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}