package com.mobileapps.lesson2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class AccountActivity : AppCompatActivity() {

    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var nextButton: MaterialButton

    private val credentialsManager = CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        emailTextInputLayout = findViewById(R.id.emailTextInputLayout)
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout)
        nextButton = findViewById(R.id.nextButton)
        val registerNowTextView = findViewById<TextView>(R.id.registerNowTextView)

        registerNowTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        nextButton.setOnClickListener {
            validateAndLogin()
        }

    }
    private fun validateAndLogin (){
        val email = emailTextInputLayout.editText?.text.toString().trim()
        val password = passwordTextInputLayout.editText?.text.toString().trim()

        if (!credentialsManager.isEmailValid(email)) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
        } else if (!credentialsManager.isPasswordValid(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) }
    }


}