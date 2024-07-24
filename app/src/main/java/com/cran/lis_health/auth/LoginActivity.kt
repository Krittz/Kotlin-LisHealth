package com.cran.lis_health.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.cran.lis_health.MainActivity
import com.cran.lis_health.R
import com.cran.lis_health.controller.UserController


class LoginActivity : AppCompatActivity() {
    private lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userController = UserController(this)

        val loginEmail: AppCompatEditText = findViewById(R.id.loginEmail)
        val loginPassword: AppCompatEditText = findViewById(R.id.loginPassword)
        val btnRedirect: AppCompatTextView = findViewById(R.id.redirectSignup)
        val btnLogin: AppCompatButton = findViewById(R.id.btnLogin)



        btnRedirect.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val email = loginEmail.text.toString().trim()
            val password = loginPassword.text.toString().trim()
            val validationResult = validate(email, password)
            if (validationResult != null) {
                Toast.makeText(this, validationResult, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultMessage = userController.login(email, password)
            Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()

            if (resultMessage == "Login bem-sucedido!") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validate(email: String, password: String): String? {
        if (email.isBlank() || password.isBlank()) {
            return "Todos os campos devem ser preenchidos."
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Endereço de email inválido."
        }
        return null
    }
}
