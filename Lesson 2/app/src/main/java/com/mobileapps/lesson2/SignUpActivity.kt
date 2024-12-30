package com.mobileapps.lesson2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {

    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var registerButton: MaterialButton
    private lateinit var loginTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailTextInputLayout = findViewById(R.id.emailTextInputLayout)
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout)
        registerButton = findViewById(R.id.nextButton)
        loginTextView = findViewById(R.id.loginTextView)

        loginTextView.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            registerAccount()
        }
    }

    private fun registerAccount() {
        val email = emailTextInputLayout.editText?.text.toString().trim()
        val password = passwordTextInputLayout.editText?.text.toString().trim()
        var isValid = true

        if (!CredentialsManager.isEmailValid(email)) {
            emailTextInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            emailTextInputLayout.error = null
        }

        if (!CredentialsManager.isPasswordValid(password)) {
            passwordTextInputLayout.error = "Password cannot be empty"
            isValid = false
        } else {
            passwordTextInputLayout.error = null
        }

        if (isValid) {
            val isRegistered = CredentialsManager.register(email, password)
            if (isRegistered) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Email is already taken", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
