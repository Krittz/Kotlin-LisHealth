package com.cran.lis_health.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.cran.lis_health.MainActivity

import com.cran.lis_health.R


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginEmail: AppCompatEditText = findViewById(R.id.loginEmail)
        val loginPassword: AppCompatEditText = findViewById(R.id.loginPassword)
        val btnRedirect: AppCompatTextView = findViewById(R.id.redirectSignup)
        val btnLogin: AppCompatButton = findViewById(R.id.btnLogin)

        btnRedirect.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
