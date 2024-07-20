package com.cran.lis_health.auth

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.cran.lis_health.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signupFullName: AppCompatEditText = findViewById(R.id.signupName)
        val signupEmail: AppCompatEditText = findViewById(R.id.signupEmail)
        val signupPassword: AppCompatEditText = findViewById(R.id.signupPassword)
        val signupConfirmPassword: AppCompatEditText = findViewById(R.id.signupConfirmPassword)
        val btnSignup: AppCompatButton = findViewById(R.id.btnSignup)
        val btnRedirectLogin: AppCompatTextView = findViewById(R.id.redirectLogin)

        btnRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}